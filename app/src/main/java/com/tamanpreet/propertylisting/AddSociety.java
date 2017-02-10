package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddSociety  extends Activity implements OnItemSelectedListener {
    
	Button submit;
	EditText sellerName,number,address,price,society;
	Spinner area;
	RadioGroup condition,floor,category;
	TextView sCitySector;
	String sName="",sAddress="",sCity="",sSector="",sCondition="",sCategory="",sFloor="",sSociety="",s;
	double sPrice; String sNumber="";
	CheckBox full,ground,first,second,basement;
	Spinner spinnerLocality;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

	DatabaseHandler db=new DatabaseHandler(AddSociety.this);
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.addsociety);
        
        sellerName=(EditText)findViewById(R.id.sellerName);
        number=(EditText)findViewById(R.id.contactNubmer);
        address=(EditText)findViewById(R.id.address);
        price=(EditText)findViewById(R.id.askingPrice);
        society=(EditText)findViewById(R.id.societyName);
        sCitySector=(TextView)findViewById(R.id.sAddress);
        
        spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaSociety);
        submit=(Button)findViewById(R.id.submit);
        
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
		sSociety=society.getText().toString().trim();
		sPrice=Double.parseDouble(price.getText().toString().trim());
		sAddress=address.getText().toString().trim();
		sCity=spinnerLocality.getSelectedItem().toString().trim();
		
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
		
		switch(floor.getCheckedRadioButtonId()){
		case R.id.first:
			sFloor="First";
			break;
		case R.id.second:
			sFloor="Second";
			break;	
		case R.id.ground:
			sCondition="Ground";
			break;	
		case R.id.top:
		sCondition="Top";
			break;	
		}
		
		switch(category.getCheckedRadioButtonId()){
		case R.id.catA:
			sCategory="A";
			break;
		case R.id.catB:
			sCategory="B";
			break;	
		}
		db.newSociety(sName, sAddress, sSociety, sCity, sSector, sNumber, sPrice, sCondition, sFloor, sCategory);
		Log.d("name", ""+sName);
		Log.d("addr", ""+sAddress);
		Log.d("soc", ""+sSociety);
		Log.d("city", ""+sCity);
		Log.d("name", ""+sSector);
		Log.d("num", ""+sNumber);
		Log.d("price", ""+sPrice);
		Log.d("cond", ""+sCondition);
		Log.d("floor", ""+sFloor);
		Log.d("category", ""+sCategory);
			if (isValidMobile(sNumber)){
		Intent i=new Intent(getApplicationContext(),SelectAddType.class);
		startActivity(i);
		finish();
		}}
	});
}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item

		String label = parent.getItemAtPosition(position).toString();
	    sCity=label;
	  
		if (m_intSpinnerInitiCount < NO_OF_EVENTS) {
	        m_intSpinnerInitiCount++;
	    
	    }else{
        AlertDialog.Builder builder = new AlertDialog.Builder(AddSociety.this);
	   
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
	private boolean isValidMobile(String phone)
	{
		boolean check=false;

		if(phone.length()<10 || phone.length()>10)
		{
			check=false;
			Toast.makeText(getApplicationContext(), "moblie no not Valid", Toast.LENGTH_LONG).show();
		}
		else {check=true;}

		return check;
	}

}
