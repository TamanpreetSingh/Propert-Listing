package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SelectSearchType extends Activity implements OnClickListener{
	Button sco,kothi,booth,housingBoard,society;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.selectsearchtype);
		sco=(Button)findViewById(R.id.scoSearch);
		kothi=(Button)findViewById(R.id.kothiSearch);
		booth=(Button)findViewById(R.id.boothSearch);
		housingBoard=(Button)findViewById(R.id.hbSearch);
		society=(Button)findViewById(R.id.societySearch);
		
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
		case R.id.boothSearch:
			Intent i=new Intent(getApplicationContext(),SearchBooth.class);
		   startActivity(i);
		   finish();
		   break;
		case R.id.scoSearch:
			Intent i1=new Intent(getApplicationContext(),SearchSco.class);
			   startActivity(i1);
			   finish();
			break;	
		case R.id.kothiSearch:
			Intent i2=new Intent(getApplicationContext(),SearchKothi.class);
			   startActivity(i2);
			   finish();
			break;
		case R.id.hbSearch:
			Intent i3=new Intent(getApplicationContext(),SearchHb.class);
			   startActivity(i3);
			   finish();
			break;	
		case R.id.societySearch:
			Intent i4=new Intent(getApplicationContext(),SearchSociety.class);
			   startActivity(i4);
			   finish();
			break;	
	}

}
}