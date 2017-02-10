package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SelectAddType extends Activity implements OnClickListener{

Button sco,kothi,booth,housingBoard,society;
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.selectaddtype);
			sco=(Button)findViewById(R.id.sco);
			kothi=(Button)findViewById(R.id.kothi);
			booth=(Button)findViewById(R.id.booth);
			housingBoard=(Button)findViewById(R.id.housingBoard);
			society=(Button)findViewById(R.id.society);
			
			kothi.setOnClickListener(this);
			housingBoard.setOnClickListener(this);
			society.setOnClickListener(this);
			sco.setOnClickListener(this);
			booth.setOnClickListener(this);
			
		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.booth:
		Intent i=new Intent(getApplicationContext(),AddBooth.class);
	   startActivity(i);
	   finish();
	   break;
	case R.id.sco:
		Intent i1=new Intent(getApplicationContext(),AddSco.class);
		   startActivity(i1);
		   finish();
		   finish();
		break;	
	case R.id.kothi:
		Intent i2=new Intent(getApplicationContext(),AddKothi.class);
		   startActivity(i2);
		   finish();
		break;
	case R.id.housingBoard:
		Intent i3=new Intent(getApplicationContext(),AddHousingBoard.class);
		   startActivity(i3);
		   finish();
		break;	
	case R.id.society:
		Intent i4=new Intent(getApplicationContext(),AddSociety.class);
		   startActivity(i4);
		   finish();
		break;	
	}
	}

}
