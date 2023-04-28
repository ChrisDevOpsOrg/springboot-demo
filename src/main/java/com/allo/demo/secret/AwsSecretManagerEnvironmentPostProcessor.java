package com.allo.demo.secret;

import com.alibaba.fastjson.JSONObject;
import com.allo.demo.secret.datasource.AwsPropertySource;
import com.allo.demo.secret.datasource.AwsSecretDataSource;
import com.allo.demo.secret.property.AwsSecretProperties;
import com.allo.demo.secret.utils.AwsSecretManagerConfigUtils;
import com.allo.demo.secret.utils.AwsSecretManagerInvokeUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.HashMap;
import java.util.Map;

public class AwsSecretManagerEnvironmentPostProcessor implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent>, Ordered {
	private static final DeferredLog LOGGER = new DeferredLog();
	public static final String SECRET_RDS = "AwsRDSProperty";
	public static final String SECRET_OTHER = "AwsOtherProperty";
	private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		LOGGER.info("connecting to aws secretsmanager");
		MutablePropertySources propertySources = environment.getPropertySources();
		if (!(environment instanceof StandardServletEnvironment)) {
			LOGGER.info("failed to connect to secretsmanager");
			return;
		}
		// jdbc password
		loadJdbcPropertySource(environment, application, propertySources);
		
		loadSecretPropertySource(environment, application, propertySources);
	}

	/**
	 * Get Database connection from secretsmanager
	 * 
	 * @param environment
	 * @param application
	 * @param propertySources
	 */
	private void loadJdbcPropertySource(ConfigurableEnvironment environment, SpringApplication application, MutablePropertySources propertySources) {
		AwsSecretProperties awsPropertis = AwsSecretManagerConfigUtils.buildDataSourceConfigProperties(environment);
		LOGGER.info("datasource secret properties:" + JSONObject.toJSONString(awsPropertis));
		try {
			Map<String, AwsSecretDataSource> map = new HashMap<>();
			String secret = AwsSecretManagerInvokeUtils.getSecret(awsPropertis);
			AwsSecretDataSource dataSource = objectMapper.readValue(secret, AwsSecretDataSource.class);
			map.put("spring.datasource", dataSource);
			AwsPropertySource awsPropertySource = AwsPropertySource.of(SECRET_RDS, map);
			propertySources.addFirst(awsPropertySource);
		} catch (Exception e) {
			LOGGER.info("load aws datasouce secret error={}", e);
		}
	}

	/**
	 * 
	 * @param environment
	 * @param application
	 * @param propertySources
	 */
	private void loadSecretPropertySource(ConfigurableEnvironment environment, SpringApplication application, MutablePropertySources propertySources) {
		AwsSecretProperties awsPropertis = AwsSecretManagerConfigUtils.buildOtherConfigProperties(environment);
		LOGGER.info("key secret properties:" + JSONObject.toJSONString(awsPropertis));
		try {
			String secret = AwsSecretManagerInvokeUtils.getSecret(awsPropertis);
			AwsPropertySource awsPropertySource = AwsPropertySource.of(SECRET_OTHER, secret);
			propertySources.addFirst(awsPropertySource);
		} catch (Exception e) {
			LOGGER.info("load aws datasouce secret error={}", e);
		}
	}


	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		LOGGER.replayTo(AwsSecretManagerEnvironmentPostProcessor.class);
	}

}
