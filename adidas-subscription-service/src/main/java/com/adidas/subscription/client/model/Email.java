package com.adidas.subscription.client.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Bean used for Email Feign client.
 * Stores the email to be used along the connection.
 * 
 * @author nacho
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email implements Serializable {

	private String email;
}
