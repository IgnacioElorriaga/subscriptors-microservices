package com.adidas.subscription.service.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.adidas.subscription.service.exceptions.InvalidParamException;

public enum Gender {

	MALE, FEMALE, OTHER;
	
	public static List<String> elementsEnum() {
		return Stream.of(Gender.values())
                .map(Gender::name)
                .collect(Collectors.toList());
		
	}
	
	public static Gender obtainValue(final String value) {
		try {
			return Gender.valueOf(value.toUpperCase());
			} catch(Exception e){
			throw new InvalidParamException("The gender field is not valid. You can select one of: " + Gender.elementsEnum()); 
		}
				
	}
	
	public static String lookup(Gender gender) {
		return gender.name().toLowerCase();
		
	}
}
