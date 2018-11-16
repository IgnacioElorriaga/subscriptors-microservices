package com.adidas.event;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("songs")
@Data
public class Songs {

	private List<String> names;
	
}
