package com.allo.demo.secret.property;

import com.allo.demo.secret.AwsPropertyConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(AwsPropertyConstant.CONFIG_PREFIX_OTHER)
public class AwsOtherSourcePropertis extends AwsSecretProperties{
	
}
