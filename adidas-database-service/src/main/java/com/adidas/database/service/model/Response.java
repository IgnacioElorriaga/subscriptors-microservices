package com.adidas.database.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Response of the Database", value = "Response")
public class Response implements Serializable {
	
	@ApiModelProperty(value = "Subscription ID generated", required = true, example = "123456")
	public Integer subscriptionId;
}
