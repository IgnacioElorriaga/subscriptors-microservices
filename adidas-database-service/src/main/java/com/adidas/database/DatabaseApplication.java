package com.adidas.database;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.adidas.database.repository.SubscriptionRepository;
import com.adidas.database.service.model.Subscription;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
//@EnableDiscoveryClient
//@EnableJpaRepositories
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Slf4j
@ComponentScan("com.adidas.database.service.model")
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner start(SubscriptionRepository repo) {
//		return (args) -> {
//			repo.save(Subscription.builder()
//					.consent(Boolean.TRUE)
//					.dateOfBirth(LocalDate.now())
//					.email("abc@de.com")
//					.firstName("Test")
//					.gender("male")
//					.newsletterId(1).build());
//			log.info("Number of elements {}", repo.count());
//		};
//	}
}
