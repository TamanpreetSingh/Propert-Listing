package com.tamanpreet.propertylisting;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class forgotPassword extends Activity implements OnClickListener {

	TextView t1, t2, t3;
	EditText e1;
	Button b;
	public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot);
		checkAndRequestPermissions();

		t1 = (TextView) findViewById(R.id.enterID);
		t2 = (TextView) findViewById(R.id.Pass);
		e1 = (EditText) findViewById(R.id.e1);
		b = (Button) findViewById(R.id.getPass);
		b.setOnClickListener(this);
	}

	private boolean checkAndRequestPermissions() {
		int sms = ContextCompat.checkSelfPermission(forgotPassword.this, Manifest.permission.SEND_SMS);
		int storage = ContextCompat.checkSelfPermission(forgotPassword.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		List<String> listPermissionsNeeded = new ArrayList<>();

		if (sms != PackageManager.PERMISSION_GRANTED) {
			listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
		}
		if (storage != PackageManager.PERMISSION_GRANTED) {
			listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}
		if (!listPermissionsNeeded.isEmpty()) {
			ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
					(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String user = e1.getText().toString();
		DatabaseHandler db = new DatabaseHandler(this);
		String pass = db.forgotPassword(user);
		String phone = db.forgotPassword1(user);
		if (!pass.equals("")) {
			new AlertDialog.Builder(forgotPassword.this)
					.setTitle("Are you sure ?")
					.setMessage("The password will be send to registered no")
					.setInverseBackgroundForced(true)
					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							message();
						}
					})
					.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.create().show();



			//t2.setText(pass);
		} else {
			Toast.makeText(getApplicationContext(), "No such User", 2000).show();
		}
	}

	public void message()
{
	String user = e1.getText().toString();
	DatabaseHandler db = new DatabaseHandler(this);
	String pass = db.forgotPassword(user);
	String phone = db.forgotPassword1(user);

	try {
		SmsManager t = SmsManager.getDefault();
		t.sendTextMessage(phone,
				null,
				pass,
				null,
				null);
		Toast.makeText(this, "Your sms has successfully sent!", 1000).show();
	} catch (Exception ex) {
		ex.printStackTrace();
	}

}



}
