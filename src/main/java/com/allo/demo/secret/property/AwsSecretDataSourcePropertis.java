package com.allo.demo.secret.property;

import com.allo.demo.secret.AwsPropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(AwsPropertyConstant.CONFIG_PREFIX_JDBC)
public class AwsSecretDataSourcePropertis extends AwsSecretProperties{
	
}
