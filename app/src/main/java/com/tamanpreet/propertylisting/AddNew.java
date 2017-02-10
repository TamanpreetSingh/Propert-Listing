package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
public class AddNew extends Activity implements OnItemSelectedListener{
Spinner spinnerType,spinnerLocality;
Button submit;
EditText name,number,price,address;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnew);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		spinnerLocality=(Spinner)findViewById(R.id.spinnerLocality);
		spinnerType=(Spinner)findViewById(R.id.spinnerType);
		submit=(Button)findViewById(R.id.submit);
		name=(EditText)findViewById(R.id.name);
		number=(EditText)findViewById(R.id.number);
		price=(EditText)findViewById(R.id.price);
		address=(EditText)findViewById(R.id.address);
		spinnerLocality.setOnItemSelectedListener(this);
		spinnerType.setOnItemSelectedListener(this);
		Log.d("Check","--1--");
		loadSpinnerType();
		Log.d("Check","--1--");
		loadSpinnerLocality();
		Log.d("Check","--3--");	
		
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sellername=name.getText().toString().trim();
				int sellernumber=Integer.parseInt(number.getText().toString().trim());
				String selleraddress=address.getText().toString().trim();
			    double sellerprice=Double.parseDouble(price.getText().toString().trim());
			    String sellerarea=spinnerLocality.getSelectedItem().toString().trim();
			    String housetype=spinnerType.getSelectedItem().toString().trim();
				DatabaseHandler db=new DatabaseHandler(AddNew.this);	
				//db.newEntry(sellername, selleraddress, sellerarea, housetype, sellernumber, sellerprice);
				
			}
		});
}

private void loadSpinnerType() {
	// TODO Auto-generated method stub
	DatabaseHandler db=new DatabaseHandler(AddNew.this);
	List<String> typeLabel=db.type();
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, typeLabel);

    // Drop down layout style - list view with radio button
    dataAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinnerType.setAdapter(dataAdapter);
	
}

private void loadSpinnerLocality() {
	// TODO Auto-generated method stub
	DatabaseHandler db=new DatabaseHandler(AddNew.this);
	List<String> sectorLabel=db.sector();
	ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, sectorLabel);

    // Drop down layout style - list view with radio button
    dataAdapter1
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinnerLocality.setAdapter(dataAdapter1);
	
}




@Override
public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
	// TODO Auto-generated method stub
	String label = parent.getItemAtPosition(position).toString();
	 
    // Showing selected spinner item
    Toast.makeText(parent.getContext(), "You selected: " + label,Toast.LENGTH_LONG).show();
}

@Override
public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
}
}
