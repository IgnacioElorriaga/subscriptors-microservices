package com.adidas.email.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Email Request", value = "Email")
public class Email implements Serializable {
	@ApiModelProperty(value = "Email address information", required = true, example = "john.smith@abc.com")
	private String email;
}
