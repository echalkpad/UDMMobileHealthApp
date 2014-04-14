package com.udm.healthmonitor;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	
	private String[] activities = {"BloodPressureMenu", "BloodSugarManualEntryActivity", "TemperatureManualEntryActivity"};
	private String[] activitiesNames = {"Blood Pressure", "Blood Sugar", "Temperature"};
	private static final String PACKAGE_NAME = "com.udm.healthmonitor.";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, activitiesNames));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Bundle extras = getIntent().getExtras();
		String value = null;
		if (extras != null) {
		    value = extras.getString(getString(R.string.user_name));
		}
		
		String selectedActivity = activities[position];
	
		try {
			Class activityClassName = Class.forName(PACKAGE_NAME+selectedActivity);
			Intent openActivity = new Intent(Menu.this, activityClassName);
			openActivity.putExtra(getString(R.string.user_name), value);
			startActivity(openActivity);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = null;
		switch(item.getItemId() ){
		case R.id.aboutUS:
			i = new Intent(PACKAGE_NAME + "About");
			startActivity(i);
			break;
		case R.id.preferences:
			i = new Intent(PACKAGE_NAME + "Prefes");
			startActivity(i);
			break;
		case R.id.exit:
			finish();
			break;
		}
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.menu, menu);
		return true;
		
		
	}
	
	

}
