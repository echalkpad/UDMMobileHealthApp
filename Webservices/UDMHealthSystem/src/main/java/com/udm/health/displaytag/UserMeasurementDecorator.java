package com.udm.health.displaytag;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserMeasurement;
import com.udm.health.domain.hibernate.UserMeasurementAttribute;
import com.udm.health.service.UserMeasurementAttributeService;

@Component
public class UserMeasurementDecorator{
	
	@Autowired
	private UserMeasurementAttributeService userMeasurementAttributeService;
	private static final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	
	
	public String getDate(UserMeasurement userMeasurement){
		return date.format(userMeasurement.getDate());
	}
	
	public String getTime(UserMeasurement userMeasurement){
		return time.format(userMeasurement.getDate());
	}
	
	public String getMeasurementType(UserMeasurement userMeasurement){
		return userMeasurement.getMeasurementType().getCode();
	}

	public String getOpeningTable(User user){
		return "<h3>Measurement List for "+user.getFirstName()+" "+user.getLastName()+"</h3><br><table class=\"listTable\">";
	}
	
	
	public String getClosingTable(){
		return "</table>";
	}
	
	
	public String getRow(UserMeasurement measurement){
		StringBuilder row = new StringBuilder();
		row.append("<tr><td>"+getMeasurementType(measurement)+"</td>");
		row.append("<td>"+getDate(measurement)+"</td>");
		row.append("<td>"+getTime(measurement)+"</td>");
		addAttributes(row, measurement.getId());
		row.append("</tr>");
		return row.toString();
	}
	
	private void addAttributes(StringBuilder row, Long id){
		List<UserMeasurementAttribute> attributeList = userMeasurementAttributeService.findMeasurementAttributesByUserMeasurementId(id);
		for(UserMeasurementAttribute attribute : attributeList){
			row.append("<td>"+attribute.getMeasurementAttribute().getName()+"</td>");
			row.append("<td>"+attribute.getValue()+"</td>");
		}
	}
}
