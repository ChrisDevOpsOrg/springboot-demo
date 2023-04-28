package com.allo.demo.secret.utils;

import com.allo.demo.secret.AwsPropertyConstant;
import com.allo.demo.secret.property.AwsOtherSourcePropertis;
import com.allo.demo.secret.property.AwsSecretDataSourcePropertis;
import com.allo.demo.secret.property.AwsSecretProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

public class AwsSecretManagerConfigUtils {
	private static final Logger log = LoggerFactory
			.getLogger(AwsSecretManagerInvokeUtils.class);
	// Set Default AWS_REGION to ap-southeast-1
	public static final String DEFAULT_REGION = "ap-southeast-1";
	public static AwsSecretProperties buildDataSourceConfigProperties(
			ConfigurableEnvironment environment) {
		AwsSecretDataSourcePropertis awsSecretManagerConfig= new AwsSecretDataSourcePropertis();
		Binder binder = Binder.get(environment);
		ResolvableType type = ResolvableType.forClass(AwsSecretDataSourcePropertis.class);
		Bindable<?> target = Bindable.of(type).withExistingValue(awsSecretManagerConfig);
		binder.bind(AwsPropertyConstant.CONFIG_PREFIX_JDBC, target);
		if(StringUtils.isEmpty(awsSecretManagerConfig.getRegion())) {
			awsSecretManagerConfig.setRegion(DEFAULT_REGION);
		}
		return awsSecretManagerConfig;
	}
	
	public static AwsSecretProperties buildOtherConfigProperties(
			ConfigurableEnvironment environment) {
		AwsOtherSourcePropertis awsSecretManagerConfig= new AwsOtherSourcePropertis();
		Binder binder = Binder.get(environment);
		ResolvableType type = ResolvableType.forClass(AwsOtherSourcePropertis.class);
		Bindable<?> target = Bindable.of(type).withExistingValue(awsSecretManagerConfig);
		binder.bind(AwsPropertyConstant.CONFIG_PREFIX_OTHER, target);
		if(StringUtils.isEmpty(awsSecretManagerConfig.getRegion())) {
			awsSecretManagerConfig.setRegion(DEFAULT_REGION);
		}
		return awsSecretManagerConfig;
	}
}
