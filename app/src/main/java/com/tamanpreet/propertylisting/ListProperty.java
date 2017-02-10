package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.Button;

public class ListProperty extends Activity {

	Button society,booth,sco,kothi,hb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_property);
	
		society=(Button)findViewById(R.id.list_society);
		booth=(Button)findViewById(R.id.list_booth);
		sco=(Button)findViewById(R.id.list_sco);
		kothi=(Button)findViewById(R.id.list_kothi);
		hb=(Button)findViewById(R.id.list_housingBoard);
	
	
		  society.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),SocietyList.class);
				startActivity(i);
				}
			});
	
		  booth.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),BoothList.class);
				startActivity(i);
				}
			});
	
	
		  sco.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),ScoList.class);
				startActivity(i);
				}
			});
		  
		  
		  hb.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),HousingBoardList.class);
				startActivity(i);
				}
			});
		  
		  
		  kothi.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),KothiList.class);
				startActivity(i);
				}
			});
	}

	


}
