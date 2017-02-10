package com.tamanpreet.propertylisting;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
public class AddBooth extends Activity implements OnItemSelectedListener{
    
	Spinner spinnerLocality;
	Button submit;
	EditText sellerName,number,address,price;
	TextView sCitySector;
	Spinner city;
	RadioGroup condition;
	String sName="",sAddress="",sCondition="",sCity="",sSector="",s, sNumber; double sPrice;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

	DatabaseHandler db=new DatabaseHandler(AddBooth.this);
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.addbooth);
        sellerName=(EditText)findViewById(R.id.sellerName);
        number=(EditText)findViewById(R.id.contactNubmer);
        address=(EditText)findViewById(R.id.address);
        sCitySector=(TextView)findViewById(R.id.sAddress);
        price=(EditText)findViewById(R.id.askingPrice);
        spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaB);
        condition=(RadioGroup)findViewById(R.id.radioCondition);
        
        submit=(Button)findViewById(R.id.submit);
        loadSpinnerLocality();
        spinnerLocality.setOnItemSelectedListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			sName=sellerName.getText().toString().trim();
			Log.d(sName,"name");
			sNumber= number.getText().toString().trim();
			Log.d(sNumber+"","number");
			sPrice=Double.parseDouble(price.getText().toString().trim());
			Log.d(sPrice+"","price");
			
			sAddress=address.getText().toString().trim();
			Log.d(sAddress,"add");
			sCity=spinnerLocality.getSelectedItem().toString().trim();
			
			Log.d(sCity,"city");
			Log.d(sSector,"sector");
			switch(condition.getCheckedRadioButtonId()){
			case R.id.new1:
				sCondition="new";
				break;
			case R.id.old:
				sCondition="old";
				break;	
			case R.id.underConstruction:
				sCondition="Under Construction";
				break;	
			}
			Log.d(sCondition, "condition");
			//sSector="";
		Toast.makeText(getApplicationContext(),sSector, 1000).show();	
	boolean a=db.newBooth(sName, sAddress, sCity, sSector, sNumber, sPrice, sCondition);
			Log.d("sql", a + "");
			//Intent i=new Intent(getApplicationContext(),SelectAddType.class);
			//startActivity(i);
			//finish();
				if (isValidMobile(sNumber))
			Toast.makeText(getApplicationContext(),"Record entered", 1000).show();
				finish();
			}
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
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item

		String label = parent.getItemAtPosition(position).toString();
	    sCity=label;

		if (m_intSpinnerInitiCount < NO_OF_EVENTS) {
	        m_intSpinnerInitiCount++;
	       
	    }else{
		
	   
	    AlertDialog.Builder builder = new AlertDialog.Builder(AddBooth.this);
	   
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
	    	        	   Log.d(sSector,"pkl");
	    	        	   s=sSector+"  "+sCity;
	    	        	   sCitySector.setText(s);
	    	        	  
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
	   
	    sCitySector.setText(s);
		   
	    AlertDialog alert = builder.create();
	    alert.show();}
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
