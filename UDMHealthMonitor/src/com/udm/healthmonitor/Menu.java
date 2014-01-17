package com.udm.healthmonitor;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	
	private String[] activities = {"DisplayMessageActivity","Dos", "Tres"};
	private static final String PACKAGE_NAME = "com.udm.healthmonitor.";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, activities));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String selectedActivity = activities[position];
		
		try {
			Class activityClassName = Class.forName(PACKAGE_NAME+selectedActivity);
			Intent openActivity = new Intent(Menu.this, activityClassName);
			startActivity(openActivity);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
