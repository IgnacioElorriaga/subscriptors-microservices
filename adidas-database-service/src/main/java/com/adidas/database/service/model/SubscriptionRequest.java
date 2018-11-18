package com.adidas.database.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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
public class SubscriptionRequest implements Serializable {
	
	@NotNull
	private String email;

	private String firstName;

	private String gender;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotNull
	private Boolean consent;
	
	@NotNull
	private Integer newsletterId;
}
