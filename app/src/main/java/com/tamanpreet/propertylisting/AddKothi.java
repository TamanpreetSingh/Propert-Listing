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
public class AddKothi extends Activity implements OnItemSelectedListener{
	Button submit;
	EditText sellerName,number,address,price;
	Spinner area,sLocality;
	RadioGroup condition,storey;
	TextView sCitySector;
	String sName="",sAddress="",sCity="",sSector="",sCondition="",sStorey="",sBasement="",sForSale="",s;
	double sPrice; String sNumber="";
	CheckBox full,ground,first,second,basement;
	Spinner spinnerType,spinnerLocality,spinnerRange;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

	DatabaseHandler db=new DatabaseHandler(AddKothi.this);
	
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.addkothi);   
        sellerName=(EditText)findViewById(R.id.sellerName);
        number=(EditText)findViewById(R.id.contactNubmer);
        address=(EditText)findViewById(R.id.address);
        price=(EditText)findViewById(R.id.askingPrice);
        sCitySector=(TextView)findViewById(R.id.sAddress);
        spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaK);
        submit=(Button)findViewById(R.id.submit);
        Log.d("condition","before");
        condition=(RadioGroup)findViewById(R.id.radioCondition);
        Log.d("condition","after");
        storey=(RadioGroup)findViewById(R.id.radioStorey);
        Log.d("storey","before");
        full=(CheckBox)findViewById(R.id.full);
        Log.d("checbox full","after");
        ground=(CheckBox)findViewById(R.id.groundC);
        first=(CheckBox)findViewById(R.id.firstC);
        Log.d("checbox fir","after");
        second=(CheckBox)findViewById(R.id.secondC);
        
        Log.d("checbox sec","after");
        basement=(CheckBox)findViewById(R.id.basement);
        Log.d("basement","after");
         loadSpinnerLocality();
        Log.d("spinner","after");
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
				break;
			case R.id.old:
				sCondition="old";
				break;	
			case R.id.underConstruction:
				sCondition="Under Construction";
				break;	
			}
			switch(storey.getCheckedRadioButtonId()){
			case R.id.single:
				sStorey="Single";
				break;
			case R.id.doubles:
				sStorey="Double";
				break;	
			case R.id.triple:
				sStorey="Triple";
				break;
			}
			if(full.isChecked()){
			sForSale+=" Full ";	
			}
			
			if(ground.isChecked()){
				sForSale+=" Ground ";	
				}
			
			if(first.isChecked()){
				sForSale+=" First ";	
				}
			
			if(second.isChecked()){
				sForSale+=" Second ";	
				}
			
			if(basement.isChecked()){
				sBasement="Yes";	
				}else{sBasement="No";}
			
	 Log.d("storey",sStorey);
	  db.newKothi(sName, sAddress, sCity, sSector, sNumber, sPrice, sCondition, sStorey, sBasement, sForSale);
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
	  
		if (m_intSpinnerInitiCount < NO_OF_EVENTS) {
	        m_intSpinnerInitiCount++;
	  
	    }else{
       
	    	AlertDialog.Builder builder = new AlertDialog.Builder(AddKothi.this);
	   
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
			Toast.makeText(getApplicationContext(), "moblie no not Valid", Toast.LENGTH_LONG).show();
		}
		else {check=true;}

		return check;
	}
  
}
