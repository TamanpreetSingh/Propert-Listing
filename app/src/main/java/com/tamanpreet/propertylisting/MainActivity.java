package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
	Button add,delete,modify,search,list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button)findViewById(R.id.add);
        
           search=(Button)findViewById(R.id.search);
           list=(Button)findViewById(R.id.list);



        
          
           search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i= new Intent(getApplicationContext(),SelectSearchType.class);
			startActivity(i);
			}
		});
           
         add.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 			Intent i= new Intent(getApplicationContext(),SelectAddType.class);
 			startActivity(i);
 			}
 		});  
   
    
         list.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View arg0) {
  				// TODO Auto-generated method stub
  			Intent i= new Intent(getApplicationContext(),ListProperty.class);
  			startActivity(i);
  			}
  		});  
    
    
    
    }
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//noinspection SimplifiableIfStatement
		switch (item.getItemId()) {
			case R.id.logout:
			finish();
				return true;

	}
    
return true;
    
}}
