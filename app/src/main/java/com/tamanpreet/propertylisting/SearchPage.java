package com.tamanpreet.propertylisting;

import java.util.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
//import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v4.widget.SimpleCursorAdapter;

public class SearchPage extends Activity  implements OnItemSelectedListener  {

	Spinner spinnerType,spinnerLocality,spinnerRange;
	Button submit;
	EditText address;
	DatabaseHandler db=new DatabaseHandler(SearchPage.this);
    ArrayList<HashMap<String, String>> usersList=  new ArrayList<HashMap<String, String>>();
	
    private SimpleCursorAdapter dataAdapter;

    private ListView lstView;
    private ListAdapter listAdaptor;
	boolean bmin=false,bmax=false,btype=false,blocality=false;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.searchpage);
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
	displayListView();
	
	Log.d("on click","before");
	
}

private void displayListView() {
	// TODO Auto-generated method stub
	
	Cursor cursor = db.getAllKothi();
	String[] columns = new String[] {
		db.KEY_NAME,
		db.KEY_NUMBER,
		db.KEY_PRICE,
		db.KEY_CITY,
		db.KEY_SECTOR
	};
	
	// the XML defined views which the data will be bound to
	int[] to = new int[] { 
	R.id.shSellerName,
	R.id.shSellerNumber,
	R.id.shSellerPrice,
	R.id.shSellerCity,
	R.id.shSellerSector
	};
	
	// create the adapter using the cursor pointing to the desired data 
	//as well as the layout information
	dataAdapter = new SimpleCursorAdapter(
	this, R.layout.list_data, 
	cursor, 
	columns, 
	to,
	0);
	
	ListView listView = (ListView) findViewById(R.id.searchList);
	// Assign adapter to ListView
	listView.setAdapter(dataAdapter);

	
}

@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
	 // On selecting a spinner item
    String label = parent.getItemAtPosition(position).toString();
    AlertDialog.Builder builder = new AlertDialog.Builder(SearchPage.this);
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

private void loadSpinnerLocality() {
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

private void loadSpinnerRange() {
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
public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
}
}
