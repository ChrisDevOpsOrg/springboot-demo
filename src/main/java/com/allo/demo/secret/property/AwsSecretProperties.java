package com.allo.demo.secret.property;

public class AwsSecretProperties {
	private String secretName;
	private String region;
	private String endpoint;
	public String getSecretName() {
		return secretName;
	}
	public void setSecretName(String secretName) {
		this.secretName = secretName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}
