package com.udm.healthmonitor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{
	
	private MediaPlayer splashSound;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.splash);
		
		splashSound = MediaPlayer.create(Splash.this, R.raw.splash);
		
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean isMusicPlayeable = preferences.getBoolean("music", true);
		if(isMusicPlayeable)
			splashSound.start();
		
		Thread timer = new Thread(){
			public void run() {
				try {
					sleep(2000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					Intent mainActivity = new Intent("com.udm.healthmonitor.MainActivity");
					startActivity(mainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		splashSound.release();
		finish();
	}
	
	
	

}
