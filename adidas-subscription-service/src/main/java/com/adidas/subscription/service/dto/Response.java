package com.adidas.subscription.service.dto;

import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api("Response of the creation process")
public class Response implements Serializable{

	@ApiModelProperty(required = true, value = "Subscription ID generated", example = "12345")
	private Integer subscriptionId;
}
