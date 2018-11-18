package com.adidas.subscription.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Data
@ApiModel(description = "Request of the consumer", value = "SubscriptionRequest")
public class SubscriptionRequest implements Serializable {

	@NotNull(message = "Email must be provided")
	@ApiModelProperty(required = true, value = "The email of the user to subscribe", example = "abc@de.fg")
	private String email;
	
	@ApiModelProperty(required = false, value = "The name of the user", example = "Christian")
	private String firstName;
	
	@ApiModelProperty(required = false, value = "The gender of the user", allowableValues="male,female,other", example = "male")
	private String gender;
	
	@ApiModelProperty(required = true, value = "The date of the birth of the user", example = "2018-12-26")
	@NotNull(message = "Date of Birth must be provided")
	private String dateOfBirth;
	
	@ApiModelProperty(required = true, value = "If the user allows to receive information", example = "true")
	@NotNull(message = "The consent must be provided")
	private Boolean consent;
	
	@ApiModelProperty(required = true, value = "The ID of the newsletter to which the user is doing the process", example = "12345")
	@NotNull(message = "The newsletterId must be provided")
	private Integer newsletterId;
	
	
}
