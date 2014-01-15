package com.udm.healthmonitor;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity{
	
	private MediaPlayer splashSound;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.splash);
		
		splashSound = MediaPlayer.create(Splash.this, R.raw.splash);
		splashSound.start();
		
		Thread timer = new Thread(){
			public void run() {
				try {
					sleep(5000);
					
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
