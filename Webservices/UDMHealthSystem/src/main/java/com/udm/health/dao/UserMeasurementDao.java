package com.udm.health.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MeasurementType;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserMeasurement;

@Component
public class UserMeasurementDao extends BaseDao<UserMeasurement, Long>{
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date parseDate(String dateInString, String timeInString) {
		Date date = null;
		try {
			date = formatter.parse(dateInString+" "+timeInString);
		} catch (ParseException e) {
			date = Calendar.getInstance().getTime();
		}
		return date;
	}

	public UserMeasurementDao() {
		super(UserMeasurement.class);
	}
	
	public UserMeasurement saveUserMeasurement(User user, String date, String time, MeasurementType measurementType){
		UserMeasurement userMeasurement = new UserMeasurement();
		userMeasurement.setDate(parseDate(date, time));
		userMeasurement.setUser(user);
		userMeasurement.setMeasurementType(measurementType);
		save(userMeasurement);
		return userMeasurement;
	}
	
}
