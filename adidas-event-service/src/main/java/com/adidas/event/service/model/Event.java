package com.adidas.event.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(description = "Request with the info to process", value = "Event")
public class Event implements Serializable {

	@ApiModelProperty(value = "Subscription ID informtation", required = true, example = "12345")
	private Integer subscriptionId;
}
