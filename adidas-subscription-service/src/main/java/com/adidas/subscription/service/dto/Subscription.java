package com.adidas.subscription.service.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
public class Subscription implements Serializable {

	private String email;
	
	private String firstName;
	
	private Gender gender;
	
	private LocalDate dateOfBirth;
	
	private Boolean consent;
	
	private Integer newsletterId;
	
	private Integer subscriptionId;
}
