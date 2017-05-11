package com.kwitpiotr.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatUtil {

	/**
	 * Date pattern
	 */
	private static final String pattern = "dd-MM-yyyy";
	
	/**
	 * Date formatter
	 */
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	
	/**
	 * Formatting date to String with pattern 
	 * @param date which is formatting
	 * @return formatted date
	 */
	public static String format(LocalDate date){
		if(date.equals(null))
			return null;
		return formatter.format(date);
	}
	
	/**
	 * Parsing date in String into date as LocalDate
	 * @param dateInString
	 * @return date as LocalDate or null if can't parse date
	 */
	public static LocalDate parse(String dateInString){
		try{
			return formatter.parse(dateInString, LocalDate::from);
		}catch(DateTimeParseException e){
			return null;
		}
	}
	
	/**
	 * Checks if the String is valid date
	 * @param dateInString
	 * @return true if String is valid date
	 */
	public static boolean validDate(String dateInString){
		return DateFormatUtil.parse(dateInString) != null;
	}
}
