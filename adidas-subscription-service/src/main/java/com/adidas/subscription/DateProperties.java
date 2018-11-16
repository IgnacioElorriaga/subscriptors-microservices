package com.adidas.subscription;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "date")
@Data
public class DateProperties {
	
	private String format;

}
