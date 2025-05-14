package com.cd.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="userregister.module")
@EnableConfigurationProperties
public class AppConfigProperties {
	private Map<String,String> messages;
}
