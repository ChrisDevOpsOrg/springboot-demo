package com.allo.demo.secret.utils;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AwsSecretsFactory {
	private static final Logger logger = LoggerFactory
			.getLogger(AwsSecretsFactory.class);
	
	public static AWSSecretsManager createAWSSecretsManager(String region){
		return AWSSecretsManagerClientBuilder.standard().withRegion(region).build();
	}
}
