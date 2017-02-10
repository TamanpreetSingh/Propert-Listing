package com.tamanpreet.propertylisting;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UpdateSociety  extends Activity implements OnItemSelectedListener{
	Spinner spinnerLocality;
	Button submit;
	EditText sellerName,number,address,price,society;
	TextView sCitySector;
	Spinner city;
	RadioGroup condition,floor,category;
	String sName="",sAddress="",sCondition="",sCity="",sSector="",dataCity,s,sFloor="",label,sCategory="",sSociety="";
	String adres;
	int  sNumber; double sPrice;
	DatabaseHandler db=new DatabaseHandler(this);
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;
	
	RadioButton new1,old,underConstruction,groundFloor,firstFloor,secondFloor,topFloor,catA,catB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addsociety);
		sellerName=(EditText)findViewById(R.id.sellerName);
	    number=(EditText)findViewById(R.id.contactNubmer);
	    sCitySector=(TextView)findViewById(R.id.sAddress);
	    society=(EditText)findViewById(R.id.societyName);
        address=(EditText)findViewById(R.id.address);
	    price=(EditText)findViewById(R.id.askingPrice);
	    spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaSociety);
	    
	    condition=(RadioGroup)findViewById(R.id.radioCondition);
        new1=(RadioButton)findViewById(R.id.new1);
		old=(RadioButton)findViewById(R.id.old);
		underConstruction=(RadioButton)findViewById(R.id.underConstruction);

		floor=(RadioGroup)findViewById(R.id.radioFloor);
		groundFloor=(RadioButton)findViewById(R.id.ground);
		secondFloor=(RadioButton)findViewById(R.id.second);
		firstFloor=(RadioButton)findViewById(R.id.first);
		topFloor=(RadioButton)findViewById(R.id.top);
		
		category=(RadioGroup)findViewById(R.id.radioCategory);
		catA=(RadioButton)findViewById(R.id.catA);
		catB=(RadioButton)findViewById(R.id.catB);
	    submit=(Button)findViewById(R.id.submit);

	    spinnerLocality.setOnItemSelectedListener(this);
	    loadSpinnerLocality();
	  
	    Intent i=getIntent();
	    adres=i.getStringExtra("adres");
	    Log.d("update kothi","1");
	    setText(adres);
	    Log.d("update kothi",adres);
	   Log.d("settext","after");
	   
	    
	    submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sName=sellerName.getText().toString().trim();
				Log.d(sName,"name");
				sNumber=Integer.parseInt(number.getText().toString().trim());
				Log.d(sNumber+"","number");
				sPrice=Double.parseDouble(price.getText().toString().trim());
				Log.d(sPrice+"","price");
				sSociety=society.getText().toString();
				sAddress=address.getText().toString().trim();
				Log.d(sAddress,"add");
				String CCity=spinnerLocality.getSelectedItem().toString();
				if(CCity.equals("City") || CCity.equals("city") )
				{
					sCity=dataCity;
					
				}else{
					sCity=CCity;
				}
					Log.d(sCity,"scity");
					Log.d(dataCity,"datacity");
					Log.d(CCity,"Ccity");
				Log.d(sSector,"sector");
				switch(condition.getCheckedRadioButtonId()){
				
				case R.id.new1:
					Log.d("condition","new");
					sCondition="new";
					break;
				case R.id.old:
					Log.d("condition","old");
					sCondition="old";
					break;	
				case R.id.underConstruction:
					sCondition="Under Construction";
					Log.d("condition","under");
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
				sFloor="Top";
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
				db.UpdateSociety(sName, sAddress, sSociety, sCity, sSector, sNumber, sPrice, sCondition, sFloor, sCategory, adres);
				Intent i=new Intent(getApplicationContext(),SearchSociety.class);
				startActivity(i);
				finish();
			}
		});
	   
		
	}
	
	private void setText(String adres) {
		// TODO Auto-generated method stub
		Log.d("settext",adres);
		Cursor c=db.getSocietyAddressFull(adres);
		int dname=c.getColumnIndex("_sellername");
		int dphone=c.getColumnIndex("_phone");
		int daddress=c.getColumnIndex("_address");
		int dcity=c.getColumnIndex("_city");
		int dsector=c.getColumnIndex("_sector");
		int dcondition=c.getColumnIndex("_condition");
		int dprice=c.getColumnIndex("_price");
		int dcategory=c.getColumnIndex("_category");
		int dfloor=c.getColumnIndex("_floor");
		int dsociety=c.getColumnIndex("_society");
	    String lname,lphone,laddress,lsc,lprice,lcondition,lsociety,lfloor,lcategory;
		c.moveToFirst();
	    if(c!=null)
	    {   
	        do
	        {
	        	lname=c.getString(dname);
	        	lphone=c.getString(dphone);
	        	laddress=c.getString(daddress);
	        	lsc=c.getString(dsector)+"  "+c.getString(dcity);
	        	dataCity=c.getString(dcity);
	        	sSector=c.getString(dsector);
	        	 lprice=c.getString(dprice);
	        	lcondition=c.getString(dcondition);
	        	lcategory=c.getString(dcategory);
	        	lsociety=c.getString(dsociety);
	        	lfloor=c.getString(dfloor);
	        	
	        	
	        	Toast.makeText(getApplicationContext(),lcondition,5000).show();
	        }while(c.moveToNext());
		// the XML defined views which the data will be bound to
			sellerName.setText(lname);
			number.setText(lphone);
			price.setText(lprice);
			address.setText(laddress);
			sCitySector.setText(lsc);
			society.setText(lsociety);
			
			if(lcondition.equals("new")){
				
				new1.setChecked(true);
				old.setChecked(false);
				underConstruction.setChecked(false);
				
			}else if(lcondition.equals("old")){
				underConstruction.setChecked(true);
				old.setChecked(true);
				new1.setChecked(false);
			}else if(lcondition.equals("Under Construction")){
				new1.setChecked(false);
				old.setChecked(false);
				underConstruction.setChecked(true);
			}
			
	   	
      
	    
	    if(lfloor.equals("First")){
			
			firstFloor.setChecked(true);
			secondFloor.setChecked(false);
			groundFloor.setChecked(false);
			topFloor.setChecked(false);
			
		}else if(lfloor.equals("Second")){
			firstFloor.setChecked(false);
			secondFloor.setChecked(true);
			groundFloor.setChecked(false);
			topFloor.setChecked(false);
		}else if(lfloor.equals("Top")){
			firstFloor.setChecked(false);
			secondFloor.setChecked(false);
			groundFloor.setChecked(false);
			topFloor.setChecked(true);
		}else if(lfloor.equals("Ground")){
			firstFloor.setChecked(false);
			secondFloor.setChecked(false);
			groundFloor.setChecked(true);
			topFloor.setChecked(false);
		}
		
	    if(lcategory.equals("A")){
			
			catA.setChecked(true);
			catB.setChecked(false);
			
			
		}else if(lcategory.equals("old")){
			catA.setChecked(false);
			catB.setChecked(true);
			
		}
	}
}

	private void loadSpinnerLocality() {
		// TODO Auto-generated method stub
		
		List<String> sectorLabel= new ArrayList<String>();
		sectorLabel.add("City");
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
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item

		String label = parent.getItemAtPosition(position).toString();
	   sCity=label;
	 
		if (m_intSpinnerInitiCount < NO_OF_EVENTS) {
	       m_intSpinnerInitiCount++;
	    
	   }else{
	 
	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	   Cursor c;
	  
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
	
}
