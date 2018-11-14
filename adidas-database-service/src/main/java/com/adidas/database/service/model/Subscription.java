package com.adidas.database.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Subscription  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long subscriptionId;
	
	private String email;
	
	private String firstName;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private Boolean consent;
	
	private Integer newsletterId;
}
