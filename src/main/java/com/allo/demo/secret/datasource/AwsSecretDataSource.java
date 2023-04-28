package com.allo.demo.secret.datasource;


import java.util.HashMap;
import java.util.Map;


public class AwsSecretDataSource {
	private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> replace(String key) {
        Map<String, Object> target = new HashMap<>();
        if (username != null && !username.isEmpty()) {
            target.put(key + ".username", username);
        }
        if (password != null && !password.isEmpty()) {
            target.put(key + ".password", password);
        }
        return target;
    }
}
