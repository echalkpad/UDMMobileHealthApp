package com.udm.health.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

	public static XMLGregorianCalendar dateToXmlGregorianCalendar(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isLeapYear(Integer year) {
		if (year / 4 == 0) {
			if (year / 100 == 0 && year / 400 > 0) {
				return false;
			}
			return true;
		}
		return false;
	}
	
}
