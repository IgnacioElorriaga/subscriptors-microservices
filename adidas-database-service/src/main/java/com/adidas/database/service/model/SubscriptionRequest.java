package com.adidas.database.service.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class SubscriptionRequest {
private String email;
	
	private String firstName;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private Boolean consent;
	
	private Integer newsletterId;
}
