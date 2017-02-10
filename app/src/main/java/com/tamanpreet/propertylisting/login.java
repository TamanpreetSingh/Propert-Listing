package com.tamanpreet.propertylisting;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity implements OnClickListener
{
	
	Button signin,signup,clear,forgotPassword;
	EditText userid,password;
	public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		checkAndRequestPermissions();




		clear=(Button)findViewById(R.id.button1);
		signin=(Button)findViewById(R.id.button2);
		signup=(Button)findViewById(R.id.button3);
		forgotPassword=(Button)findViewById(R.id.button4);
		
		userid=(EditText)findViewById(R.id.editText1);
		password=(EditText)findViewById(R.id.editText2);
		
		clear.setOnClickListener(this);
		signin.setOnClickListener(this);
		signup.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
	}
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private boolean checkAndRequestPermissions(){
		int sms = ContextCompat.checkSelfPermission(login.this, Manifest.permission.SEND_SMS);
		int storage = ContextCompat.checkSelfPermission(login.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
		List<String> listPermissionsNeeded = new ArrayList<>();

		if (sms != PackageManager.PERMISSION_GRANTED) {
			listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
		}
		if (storage != PackageManager.PERMISSION_GRANTED) {
			listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}
		if (!listPermissionsNeeded.isEmpty())
		{
			ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
					(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
			return false;
		}
		return true;
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		try
		{
		switch (arg0.getId()) {
		case R.id.button1:
			
			userid.setText("");
			password.setText("");
			
			break;

		case R.id.button2:
		
			
			sessionmanager session=new sessionmanager(this);
			String useridd= userid.getText().toString();
			String passwordd=password.getText().toString();
			session.createloginsession(useridd, passwordd);
			
			DatabaseHandler db=new DatabaseHandler(this);
			Log.d("test","test");
			boolean b=db.select_signup(useridd, passwordd);
			Log.d("test 1", "test");
			if(useridd.trim().length()>0 && passwordd.trim().length()>0) {
				if (b) {
					//savepreferences(useridd);
					Toast.makeText(getApplicationContext(), "Login Successfully", 5000).show();
					Intent intent = new Intent().setClass(this, MainActivity.class);
					startActivity(intent);
				} else
					Toast.makeText(getApplicationContext(), "Username/Password is incorrect", 50000).show();
			}
				else
				Toast.makeText(getApplicationContext(), "Please enter Username/Password ", 50000).show();

			break;
			
			
		case R.id.button3:
			
			Intent intent=new Intent().setClass(this, signup.class);
			startActivity(intent);
			
			
			break;
		
        case R.id.button4:
			
			Intent intent1=new Intent().setClass(this, forgotPassword.class);
			startActivity(intent1);
			
			
			break;
			
		
		
		default:
			break;
		}
		}catch(Exception e)
		{
			Toast.makeText(login.this, "problem"+e.getMessage(),9000).show();
		}
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//noinspection SimplifiableIfStatement
		switch (item.getItemId()) {
			case R.id.menu_settings:
				TextView content = (TextView) getLayoutInflater().inflate(R.layout.about_view, null);
				content.setMovementMethod(LinkMovementMethod.getInstance());
				content.setText(Html.fromHtml(getString(R.string.about_body)));
				new AlertDialog.Builder(this)
						.setTitle("About:")
						.setView(content)
						.setInverseBackgroundForced(true)
						.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).create().show();
				return true;
			case R.id.feedback:
				Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "kajalnayyar1697@gmail.com"));
				intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
				intent.putExtra(Intent.EXTRA_TEXT,"");
				startActivity(intent);

		}
		return true;
	}}
