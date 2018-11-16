package com.adidas.database.service.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
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

	@NotBlank(message = "Email must be provided")
	@ApiParam(required = true, value = "The email of the user to subscribe", example = "abc@de.fg")
	private String email;

	@ApiParam(required = false, value = "The name of the user", example = "Christian")
	private String firstName;

	@ApiParam(required = false, value = "The gender of the user", allowableValues = "male,female,other", example = "male")
	private String gender;

	@ApiParam(required = true, value = "The date of the birth of the user", example = "2018-12-26")
	@NotNull(message = "Date of Birth must be provided")
	private LocalDate dateOfBirth;

	@ApiParam(required = true, value = "If the user allows to receive information", example = "true")
	@NotNull(message = "The consent must be provided")
	private Boolean consent;

	@ApiParam(required = true, value = "The ID of the newsletter to which the user is doing the process", example = "12345")
	@NotNull(message = "The newsletterId must be provided")
	private Integer newsletterId;
}
