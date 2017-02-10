package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchSelect  extends Activity  implements OnItemSelectedListener {

	Spinner spinnerType,spinnerLocality,spinnerRange;
	Button submit;
	EditText address;
	DatabaseHandler db=new DatabaseHandler(SearchSelect.this);
    ArrayList<HashMap<String, String>> usersList=  new ArrayList<HashMap<String, String>>();
	
    private SimpleCursorAdapter dataAdapter;
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchselect);
		Log.d("check 5","");
		Log.d("check 6","");
		submit=(Button)findViewById(R.id.submit);
		address=(EditText)findViewById(R.id.address);
		address.setText("Address");
		address.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			address.setText("");	
			}
		});
		spinnerLocality=(Spinner)findViewById(R.id.spinnerLocality);
		spinnerType=(Spinner)findViewById(R.id.spinnerType);
		spinnerRange=(Spinner)findViewById(R.id.spinnerRange);
		 
		spinnerLocality.setOnItemSelectedListener(this);
		spinnerRange.setOnItemSelectedListener(this);
		spinnerType.setOnItemSelectedListener(this);
		
		loadSpinnerType();
		loadSpinnerLocality();
		loadSpinnerRange();
		
		Log.d("on click","before");
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sAddress=address.getText().toString();
				
				String typeValue=spinnerType.getSelectedItem().toString().trim();
				if(typeValue.length()>=1){
				}
				String localityValue=spinnerLocality.getSelectedItem().toString().trim();
				if(localityValue.length()>=1){
				}
				
			}
		});
		
		
	}
	
	private void loadSpinnerRange() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> rangeLabel= new ArrayList<String>();
		rangeLabel.add("Range");
		rangeLabel.add("All");
		rangeLabel.add("1 Lakh-10 Lakh");
		rangeLabel.add("10 Lakh-50 Lakh");
		rangeLabel.add("50 Lakh- 1 Crore");
		rangeLabel.add("1 Crore-1.5 Crore");
		rangeLabel.add("1.5 Crore & Above");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, rangeLabel);

	    // Drop down layout style - list view with radio button
	    dataAdapter
	            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    // attaching data adapter to spinner
	    spinnerRange.setAdapter(dataAdapter);

		

	}

	private void loadSpinnerLocality() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> sectorLabel= new ArrayList<String>();
		sectorLabel.add("Area");
		sectorLabel.add("All");
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

	private void loadSpinnerType() {
		// TODO Auto-generated method stub
		List<String> typeLabel= new ArrayList<String>();
		typeLabel.add("Property Type");
		typeLabel.add("All");
		typeLabel.add("Kothi");
		typeLabel.add("Housing Board Flat");
		typeLabel.add("Society Flat");
		typeLabel.add("S.C.O.");
		typeLabel.add("Booth");
		
		//db.type();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, typeLabel);

	    // Drop down layout style - list view with radio button
	    dataAdapter
	            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    // attaching data adapter to spinner
	    spinnerType.setAdapter(dataAdapter);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		 // On selecting a spinner item
	    String label = parent.getItemAtPosition(position).toString();
	    AlertDialog.Builder builder = new AlertDialog.Builder(SearchSelect.this);
	    CharSequence[] items;
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
		
	         items=chandigarhLabel.toArray(new CharSequence[chandigarhLabel.size()]);
	    
	       
	       builder.setTitle("Make your selection");
	       builder.setItems(items, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int item) {
	               // Do something with the selection
	           }   
	           
	       });
	       
	       }
	       if(label=="Panchkula"){
	       		List<String> panchkulaLabel= new ArrayList<String>();
	       		Log.d("cursor--","before--");
	       		c=db.getPanchkula();
	       		Log.d("cursor--","before--");
	       		while(c.moveToNext()){
	       		    panchkulaLabel.add(c.getString(c.getColumnIndex("_sector")));
	       		}
	       		Log.d("for--","after--");
	       		
	       	    items=panchkulaLabel.toArray(new CharSequence[panchkulaLabel.size()]);
	       	    builder.setTitle("Make your selection");
	       	       builder.setItems(items, new DialogInterface.OnClickListener() {
	       	           public void onClick(DialogInterface dialog, int item) {
	       	               // Do something with the selection
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
	   		
	   	    items=mohaliLabel.toArray(new CharSequence[mohaliLabel.size()]);
	   	    builder.setTitle("Make your selection");
	   	       builder.setItems(items, new DialogInterface.OnClickListener() {
	   	           public void onClick(DialogInterface dialog, int item) {
	   	               // Do something with the selection
	   	        }          
	   	              
	   	       });
	   	}
	       
	       
	       AlertDialog alert = builder.create();
	       alert.show();
	       
	    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
