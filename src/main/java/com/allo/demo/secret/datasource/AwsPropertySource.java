package com.allo.demo.secret.datasource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.HashMap;
import java.util.Map;

public class AwsPropertySource extends PropertiesPropertySource{

	protected AwsPropertySource(String name, Map<String, Object> source) {
		super(name, source);
	}
	
	public static AwsPropertySource of(String name, Map<String, AwsSecretDataSource> secretMap) {
        Map<String, Object> target = new HashMap<>();
        for (Map.Entry<String, AwsSecretDataSource> entry : secretMap.entrySet()) {
            String key = entry.getKey();
            AwsSecretDataSource value = entry.getValue();
            if (value == null) {
                continue;
            }
            Map<String, Object> replace = value.replace(key);
            target.putAll(replace);
        }
        return new AwsPropertySource(name, target);
    }
	
	public static AwsPropertySource of(String name, String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		
        Map<String, Object> target = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            String value = jsonObject.getString(key);
            if (value == null) {
                continue;
            }
            target.put(key, value);
        }
        return new AwsPropertySource(name, target);
    }
	
}
