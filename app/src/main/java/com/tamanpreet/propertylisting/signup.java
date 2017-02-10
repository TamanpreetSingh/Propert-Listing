package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class signup extends Activity implements OnClickListener {

	EditText fullname,userid,password,retype,dob,mobileno;
	Button clear,submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		fullname=(EditText)findViewById(R.id.editText1);
		userid=(EditText)findViewById(R.id.editText2);
		password=(EditText)findViewById(R.id.editText3);
		retype=(EditText)findViewById(R.id.editText4);
		dob=(EditText)findViewById(R.id.editText5);
		mobileno=(EditText)findViewById(R.id.editText6);
		
		clear=(Button)findViewById(R.id.button1);
		submit=(Button)findViewById(R.id.button2);

		clear.setOnClickListener(this);
		submit.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		try
		{
		switch (arg0.getId()) {
		
		case R.id.button1:
			
			fullname.setText("");
			userid.setText("");
			password.setText("");
			retype.setText("");
			dob.setText("");
			mobileno.setText("");
						
		break;
		
		
		case R.id.button2:
			
			String fullnamee=fullname.getText().toString();
			String useridd=userid.getText().toString();
			String passwordd=password.getText().toString();
			String retypee=retype.getText().toString();
			String dobb=dob.getText().toString();
			String mobilenoo=mobileno.getText().toString();
			if (fullnamee.trim().length()==0 && useridd.trim().length()==0 && passwordd.trim().length()==0 && retypee.trim().length()==0 && dobb.trim().length()==0 && mobilenoo.trim().length()==0)
			{
				Toast.makeText(getApplicationContext(),"Please fill all the detail",Toast.LENGTH_LONG).show();

			}
			else if (fullnamee.trim().length() == 0){Toast.makeText(getApplicationContext(),"Please fill fullname field",Toast.LENGTH_LONG).show();}
			else if (useridd.trim().length()==0){Toast.makeText(getApplicationContext(),"Please fill userid",Toast.LENGTH_LONG).show();}
			else if (passwordd.trim().length()==0){Toast.makeText(getApplicationContext(),"Please fill password",Toast.LENGTH_LONG).show();}
			else if (retypee.trim().length()==0){Toast.makeText(getApplicationContext(),"Please fill retype field",Toast.LENGTH_LONG).show();}
			else if (dobb.trim().length()==0){Toast.makeText(getApplicationContext(),"Please fill Date of Birth",Toast.LENGTH_LONG).show();}
			else if (mobilenoo.trim().length()==0){Toast.makeText(getApplicationContext(),"Please fill mobile number",Toast.LENGTH_LONG).show();}
			else if(isValidMobile(mobilenoo) && fullnamee.trim().length()>0 && useridd.trim().length()>0 && passwordd.trim().length()>0 && retypee.trim().length()>0 && dobb.trim().length()>0 && mobilenoo.trim().length()>0 ) {

				DatabaseHandler db = new DatabaseHandler(this);
				db.insert123(fullnamee, useridd, passwordd, retypee, dobb, mobilenoo);
				Toast.makeText(getApplicationContext(), "Inserted", 10000).show();

				Intent intent = new Intent().setClass(this, login.class);
				startActivity(intent);
			}
			
			break;

		default:
			break;
		}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), 5000).show();
		}
	}
	private boolean isValidMobile(String phone)
	{
		boolean check=false;

			if(phone.length()<10 || phone.length()>10)
			{
				check=false;
				Toast.makeText(getApplicationContext(),"moblie no not Valid",Toast.LENGTH_LONG).show();
			}
			else {check=true;}

		return check;
}}
