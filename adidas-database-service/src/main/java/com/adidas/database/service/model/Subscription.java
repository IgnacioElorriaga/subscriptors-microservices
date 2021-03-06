package com.adidas.database.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table
public class Subscription implements Serializable {
	@Id
	@GeneratedValue
	public Integer subscriptionId;
	
	private String email;
	
	private String firstName;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private Boolean consent;
	
	private Integer newsletterId;
}
