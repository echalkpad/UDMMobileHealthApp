package com.udm.healthmonitor.bloodPressure;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;

public class BloodPressure extends Activity {

	private BluetoothAdapter bluetoothAdapter;

	private boolean setUp() {
		boolean setUpStatus = true;
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Builder dialog = new AlertDialog.Builder(this);
		if (bluetoothAdapter == null) {
			dialog.setTitle("Bluetooth")
					.setMessage("Bluetooth not detected on this device")
					// .setIcon(R.drawable.ic_dialog_alert)
					.show();
			setUpStatus = false;
		} else {
			dialog.setTitle("Bluetooth Detected")
			.setMessage("Bluetooth successfully detected on this device.")
			// .setIcon(R.drawable.ic_dialog_alert)
			.show();

		}
		return setUpStatus;

	}

}
