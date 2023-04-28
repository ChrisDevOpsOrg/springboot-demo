package com.allo.demo.secret.utils;

import com.allo.demo.secret.property.AwsSecretProperties;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;

public class AwsSecretManagerInvokeUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(AwsSecretManagerInvokeUtils.class);
	
	/**
	 * Get credentials from AWS SecretsManager
	 * @param aws
	 * @return
	 * @throws IOException
	 */
	public static String getSecret(AwsSecretProperties aws) throws IOException {
		String secretName = aws.getSecretName();
		String region = aws.getRegion();
		logger.info("read secret config from secret manager, secretName={},region={}",secretName,region);
		AWSSecretsManager client = AwsSecretsFactory.createAWSSecretsManager(region);
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;
		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		} catch (Exception e) {
			throw e;
		}
		if (getSecretValueResult.getSecretString() != null) {
			return getSecretValueResult.getSecretString();
		} else {
			return new String(
					Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
		}
	}
}
