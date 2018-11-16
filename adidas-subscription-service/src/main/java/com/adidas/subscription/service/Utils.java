package com.adidas.subscription.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	/**
	 * Converts a date from String to LocalDate.<br/>
	 * It uses the format yyyy-MM-dd.<br/>
	 * It was included as a function to be used along the MS, instead of created as Converter (not needed to be included in each class).
	 * 
	 * @param date from the user to be parsed.
	 * @return the input string as a {@linkplain LocalDate}
	 */
	public static LocalDate obtainDate(final String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
