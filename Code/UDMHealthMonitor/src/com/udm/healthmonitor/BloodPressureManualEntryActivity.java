package com.udm.healthmonitor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udm.healthmonitor.bloodPressure.WebserviceResponse;

public class BloodPressureManualEntryActivity extends Activity implements OnClickListener{
	
	private WebserviceService webservice = new WebserviceService();
	private String systolic;
	private String diastolic;
	private String date;
	private String time;
	private String user;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blood_pressure_manual);
		
		Calendar calendar = GregorianCalendar.getInstance();
		date = dateFormat.format(calendar.getTime());
		time = timeFormat.format(calendar.getTime());
		((EditText) findViewById(R.id.data_entry_date)).setText(date);
		((EditText) findViewById(R.id.data_entry_time)).setText(time);
		
		Button save_button = (Button) findViewById(R.id.button_save);
		
		save_button.setOnClickListener(this);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	}

	@Override
	public void onClick(View v) {
		systolic = ((EditText) findViewById(R.id.data_entry_systolic)).getText().toString().trim();
		diastolic = ((EditText) findViewById(R.id.data_entry_diastolic)).getText().toString().trim();
		date = ((EditText) findViewById(R.id.data_entry_date)).getText().toString().trim();
		time = ((EditText) findViewById(R.id.data_entry_time)).getText().toString().trim();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    user = extras.getString(getString(R.string.user_name));
		}
		
		if(!hasInvalidFields()){
			WebserviceResponse response = webservice.sendBloodPressureMeasurement(user, systolic, diastolic, date, time);
			Toast.makeText(getApplicationContext(), "Response: "+response.getStatus(),Toast.LENGTH_LONG).show();
			resetForm();
		}
	}
	
	private void resetForm(){
		((EditText) findViewById(R.id.data_entry_systolic)).setText("");
		((EditText) findViewById(R.id.data_entry_diastolic)).setText("");
		((EditText) findViewById(R.id.data_entry_date)).setText("");
		((EditText) findViewById(R.id.data_entry_time)).setText("");
		
		systolic = "";
		diastolic =  "";
		date =  "";
		time =  "";
	}
	
	private boolean hasInvalidFields(){
		boolean hasInvalidFields = false;
		if(systolic.equals("")){
			((EditText) findViewById(R.id.data_entry_systolic)).setError(getString(R.string.error_field_required));
			hasInvalidFields = true;
		}
		if(diastolic.equals("")){
			((EditText) findViewById(R.id.data_entry_diastolic)).setError(getString(R.string.error_field_required));
			hasInvalidFields = true;
		}
		if(date.equals("")){
			((EditText) findViewById(R.id.data_entry_date)).setError(getString(R.string.error_field_required));
			hasInvalidFields = true;
		}
		if(time.equals("")){
			((EditText) findViewById(R.id.data_entry_time)).setError(getString(R.string.error_field_required));
			hasInvalidFields = true;
		}
		return hasInvalidFields;
	}
	
}
