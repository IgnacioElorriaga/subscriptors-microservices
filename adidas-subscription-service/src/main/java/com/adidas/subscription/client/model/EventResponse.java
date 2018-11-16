package com.adidas.subscription.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Bean used in the connection with the Event Feign Client.
 * 
 * @author nacho
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

	private String answer;
}
