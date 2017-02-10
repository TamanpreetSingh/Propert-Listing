package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
	
import android.os.Bundle;

public class AddHousingBoard  extends Activity implements OnItemSelectedListener {
	Button submit;
	EditText sellerName,number,address,price;
	Spinner area;
	RadioGroup condition,floor,category;
	String sName="",sAddress="",sCity="",sCondition="",sCategory="",sFloor="",sSector="",s;
	double sPrice=0; String sNumber;
	CheckBox full,ground,first,second,basement;
	TextView sCitySector;
	Spinner spinnerLocality;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

	DatabaseHandler db=new DatabaseHandler(AddHousingBoard.this);
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.addhousingboard);
        
        sellerName=(EditText)findViewById(R.id.sellerName);
        number=(EditText)findViewById(R.id.contactNubmer);
        address=(EditText)findViewById(R.id.address);
        price=(EditText)findViewById(R.id.askingPrice);
        
        spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaH);
        submit=(Button)findViewById(R.id.submit);
        sCitySector=(TextView)findViewById(R.id.sAddress);
        condition=(RadioGroup)findViewById(R.id.radioCondition);
        floor=(RadioGroup)findViewById(R.id.radioFloor);
        category=(RadioGroup)findViewById(R.id.radioCategory);
        loadSpinnerLocality();
        spinnerLocality.setOnItemSelectedListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    		sName=sellerName.getText().toString().trim();
    		sNumber=number.getText().toString().trim();
    		sPrice=Double.parseDouble(price.getText().toString().trim());
    		sAddress=address.getText().toString().trim();
    		sCity=spinnerLocality.getSelectedItem().toString().trim();
    		
    		switch(condition.getCheckedRadioButtonId()){
    		case R.id.new1:
    			sCondition="new";
    			Toast.makeText(getApplicationContext(), sCondition, 3000).show();
    			break;
    		case R.id.old:
    			sCondition="old";
    			Toast.makeText(getApplicationContext(), sCondition, 3000).show();
    			break;	
    		case R.id.underConstruction:
    			sCondition="Under Construction";
    			Toast.makeText(getApplicationContext(), sCondition, 3000).show();
    			break;	
    		}
    		
    		switch(floor.getCheckedRadioButtonId()){
    		case R.id.first:
    			sFloor="First";
    			break;
    		case R.id.second:
    			sFloor="Second";
    			break;	
    		case R.id.ground:
    			sFloor="Ground";
    			break;	
    		case R.id.top:
    			sFloor="Third";
    			break;	
    		}
    		
    		switch(category.getCheckedRadioButtonId()){
    		case R.id.hig:
    			sCategory="HIG";
    			break;
    		case R.id.lig:
    			sCategory="LIG";
    			break;	
    		case R.id.mig:
    			sCategory="MIG";
    			break;
    		case R.id.ews:
    			sCategory="EWS";
    			break;	
    		}
    	db.newHb(sName, sAddress, sCity, sSector, sNumber, sPrice, sCondition, sFloor, sCategory);
				if (isValidMobile(sNumber)){
    	Intent i=new Intent(getApplicationContext(),SelectAddType.class);
		startActivity(i);
		finish();
     	}}
    	});
	
}
	
	private void loadSpinnerLocality() {
		// TODO Auto-generated method stub
		List<String> sectorLabel= new ArrayList<String>();
		sectorLabel.add("Select Area");
		sectorLabel.add("Chandigarh");
		sectorLabel.add("Mohali");
		sectorLabel.add("Panchkula");
		
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, sectorLabel);

	    // Drop down layout style - list view with radio button
	    dataAdapter1
	            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    // attaching data adapter to spinner
	    spinnerLocality.setAdapter(dataAdapter1);
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item

		String label = parent.getItemAtPosition(position).toString();
	    sCity=label;
	  
		if (m_intSpinnerInitiCount < NO_OF_EVENTS ) {
	        m_intSpinnerInitiCount++;
	      
	    }else{
        
	    	AlertDialog.Builder builder = new AlertDialog.Builder(AddHousingBoard.this);
	   
	    Cursor c;
	    
	        // Showing selected spinner item
	   // Toast.makeText(parent.getContext(), "You selected: " + label,
	     //       Toast.LENGTH_LONG).show();

	    if(label=="Chandigarh"){
		List<String> chandigarhLabel= new ArrayList<String>();
		Log.d("cursor--","before--");
		c=db.getChandigarh();
		Log.d("cursor--","before--");
		
		while(c.moveToNext()){
		    chandigarhLabel.add(c.getString(c.getColumnIndex("_sector")));
		}
		Log.d("for--","after--");
		 final CharSequence[] items=chandigarhLabel.toArray(new CharSequence[chandigarhLabel.size()]);
	    
	       
	       builder.setTitle("Chandigarh");
	       builder.setItems(items, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int item) {
	               // Do something with the selection
	             sSector=(String) items[item];
	             s=sSector+"  "+sCity;
	        	   sCitySector.setText(s);
	             
	           }          
	           
	       });	
	    }

	    if(label=="Panchkula"){
	    		List<String> panchkulaLabel= new ArrayList<String>();
	    		Log.d("cursor--","before--");
	    		c=db.getPanchkula();
	    		Log.d("cursor--","before--");
	    	int i=0;
	    	     c.moveToFirst();
	    		while(c.moveToNext()){
	    		    panchkulaLabel.add(c.getString(c.getColumnIndex("_sector")));
	    		i++;
	    		}
	    		Log.d("for--","after--");
	    		Log.d("for--",i+"");
	    		
	    		final CharSequence[]  items=panchkulaLabel.toArray(new CharSequence[panchkulaLabel.size()]);
	    	    builder.setTitle("Panchkula");
	    	       builder.setItems(items, new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int item) {
	    	               // Do something with the selection
	    	        	   sSector=(String) items[item];
	    	        	   s=sSector+"  "+sCity;
			        	   sCitySector.setText(s);
	    	        	   Log.d(sSector,"pkl");
	    	           }          
	    	              
	    	       });
	    	}
	    
	    if(label=="Mohali"){
			List<String> mohaliLabel= new ArrayList<String>();
			Log.d("cursor--","before--");
			c=db.getMohali();
			Log.d("cursor--","before--");
			
			while(c.moveToNext()){
			    mohaliLabel.add(c.getString(c.getColumnIndex("_sector")));
			}
			Log.d("for--","after--");
			
			final CharSequence[]  items=mohaliLabel.toArray(new CharSequence[mohaliLabel.size()]);
		    builder.setTitle("Mohali");
		       builder.setItems(items, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int item) {
		               // Do something with the selection
		        	   sSector=(String) items[item];
		        	   s=sSector+"  "+sCity;
		        	   sCitySector.setText(s);
		           }          
		              
		       });
		}
	    
	    
	    AlertDialog alert = builder.create();
	    alert.show();
	}
	}
	

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
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
	}

	}
