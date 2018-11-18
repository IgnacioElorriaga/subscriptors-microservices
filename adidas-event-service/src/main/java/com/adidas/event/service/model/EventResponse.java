package com.adidas.event.service.model;

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
@ApiModel(description = "Response of the event triggered", value = "Event answer")
public class EventResponse implements Serializable{
	@ApiModelProperty(required = true, value = "Answer from the server", example = "Ping OK")
	private String answer;
}
