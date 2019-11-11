package model.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class DateHandling {
	
	public static Date toMilitaryFormat(Date date) { 
		
		//date = String.format("%s/%s/%s", date.substring(8), date.substring(5, 7), date.substring(0, 4));
		
		return date;
		
	}
	
	public static void toMilitaryFormat(DatePicker dp) { 
		dp.setConverter(new StringConverter<LocalDate>() {
	     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	     @Override 
	     public String toString(LocalDate date) {
	         if (date != null) {
	             return dateFormatter.format(date);
	         } else {
	             return "";
	         }
	     }

	     @Override 
	     public LocalDate fromString(String string) {
	         if (string != null && !string.isEmpty()) {
	             return LocalDate.parse(string, dateFormatter);
	         } else {
	             return null;
	         }
	     }
		});
	}
	
	public static final LocalDate toLocalDate (Date date){
        return Instant.ofEpochMilli(date.getTime())
        	      .atZone(ZoneId.systemDefault())
        	      .toLocalDate();
    }
}
