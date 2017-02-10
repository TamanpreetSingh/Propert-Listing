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

public class UpdateKothi extends Activity implements OnItemSelectedListener{
	Spinner spinnerLocality;
	Button submit;
	EditText sellerName,number,address,price;
	TextView sCitySector;
	Spinner city;
	RadioGroup condition,storey;
	String sName="",sAddress="",sCondition="",sCity="",sSector="",dataCity,sForSale="",s,sStorey="",sBasement="",label;
	String adres;
	int  sNumber; double sPrice;
	DatabaseHandler db=new DatabaseHandler(this);
	CheckBox full,ground,first,second,basement;
	RadioButton new1,old,underConstruction,single,doubles,triple;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.addkothi);
	 full=(CheckBox)findViewById(R.id.full);
	    first=(CheckBox)findViewById(R.id.firstC);
	  second=(CheckBox)findViewById(R.id.secondC);
	   ground=(CheckBox)findViewById(R.id.groundC);
		
	sellerName=(EditText)findViewById(R.id.sellerName);
    number=(EditText)findViewById(R.id.contactNubmer);
    sCitySector=(TextView)findViewById(R.id.sAddress);
    address=(EditText)findViewById(R.id.address);
    price=(EditText)findViewById(R.id.askingPrice);
    spinnerLocality=(Spinner)findViewById(R.id.spinnerAreaK);
    condition=(RadioGroup)findViewById(R.id.radioCondition);
    storey=(RadioGroup)findViewById(R.id.radioStorey);
    new1=(RadioButton)findViewById(R.id.new1);
	old=(RadioButton)findViewById(R.id.old);
	underConstruction=(RadioButton)findViewById(R.id.underConstruction);
	basement=(CheckBox)findViewById(R.id.basement);
	single=(RadioButton)findViewById(R.id.single);
	doubles=(RadioButton)findViewById(R.id.doubles);
	triple=(RadioButton)findViewById(R.id.triple);
	

    submit=(Button)findViewById(R.id.submit);
    
    spinnerLocality.setOnItemSelectedListener(this);
    loadSpinnerLocality();
    Intent i=getIntent();
    adres=i.getStringExtra("adres");
    Log.d("update kothi","1");
    setText(adres);
    Log.d("update kothi","2");
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
			Log.d("condition","out"+sCondition);
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
			Log.d("storey","out"+sStorey);
			Log.d("","name"+sName+" addr"+sAddress+" city"+sCity+" sector"+sSector+" number"+sNumber+" price"+sPrice+" condition"+sCondition+" storey"+sStorey+" base"+sBasement+" fsale"+sForSale+" add1"+adres);
			if(full.isChecked()){
				Log.d("full","1");
				sForSale+=" Full ";	
				}
				
				if(ground.isChecked()){
					Log.d("ground","1");
					sForSale+=" Ground ";	
					}
				
				if(first.isChecked()){
					Log.d("first","1");
					sForSale+=" First ";	
					}
				
				if(second.isChecked()){
					Log.d("second","1");
					sForSale+=" Second ";	
					}
				if(basement.isChecked()){
					Log.d("basement","1");
					sBasement="Yes";	
					}else{sBasement="No";}
				
		 Log.d("storey",sStorey);
		Log.d("",sName+sAddress+sCity+sSector+sNumber+sPrice+sCondition+sStorey+sBasement+sForSale+adres);
		   db.UpdateKothi(sName, sAddress, sCity, sSector, sNumber, sPrice, sCondition, sStorey, sBasement, sForSale, adres);
		   Intent i=new Intent(getApplicationContext(),SearchKothi.class);
			startActivity(i);
			finish();
		}
	});
 
	
}
 
	
	private void setText(String adres) {
		// TODO Auto-generated method stub
		Cursor c=db.getKothiAddressFull(adres);
		int dname=c.getColumnIndex("_sellername");
		int dphone=c.getColumnIndex("_phone");
		int daddress=c.getColumnIndex("_address");
		int dcity=c.getColumnIndex("_city");
		int dsector=c.getColumnIndex("_sector");
		int dcondition=c.getColumnIndex("_condition");
		int dprice=c.getColumnIndex("_price");
		int dstorey=c.getColumnIndex("_storey");
		int dbasement=c.getColumnIndex("_basement");
		int dforsale=c.getColumnIndex("_forsale");
	    String lname,lphone,laddress,lsc,lprice,lcondition,lstorey,lbasement,lforsale;
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
	        	lbasement=c.getString(dbasement);
	        	lstorey=c.getString(dstorey);
	        	lforsale=c.getString(dforsale);
	        	
	        	
	        	Toast.makeText(getApplicationContext(),lcondition,5000).show();
	        }while(c.moveToNext());
		// the XML defined views which the data will be bound to
			sellerName.setText(lname);
			number.setText(lphone);
			price.setText(lprice);
			address.setText(laddress);
			sCitySector.setText(lsc);
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
			
	   	String a[]=lforsale.split(" ");
        	for(String ss: a){
        		if(ss.equals("First")){
        			first.setChecked(true);
        		}else if(ss.equals("Second")){
        			second.setChecked(true);
        		}else if(ss.equals("Full")){
        			full.setChecked(true);
        		}else if(ss.equals("Ground")){
        			ground.setChecked(true);
        		}
        	}
        	
        
	    if(lbasement.equals("Yes")){
	    	basement.setChecked(true);
	    }
	    
	    if(lstorey.equals("Single")){
			
			single.setChecked(true);
			doubles.setChecked(false);
			triple.setChecked(false);
			
		}else if(lstorey.equals("Double")){
			single.setChecked(false);
			doubles.setChecked(true);
			triple.setChecked(false);
		}else if(lstorey.equals("Triple")){
			single.setChecked(false);
			doubles.setChecked(false);
			triple
			.setChecked(true);
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

	}}
