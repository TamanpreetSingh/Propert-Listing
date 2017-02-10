package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchBooth extends Activity  implements OnItemSelectedListener  {

	Spinner spinnerType,spinnerLocality;
	Button submit;
	EditText address;
	TextView tmin,tmax,sCitySector;
	DatabaseHandler db=new DatabaseHandler(SearchBooth.this);
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

    ArrayList<HashMap<String, String>> usersList=  new ArrayList<HashMap<String, String>>();
	
    String sSector,sCity,sAddress,s;
    double sMin=0.0,sMax=999999999;  
    boolean bmin=false,bmax=false,btype=false,blocality=false;
	SeekBar seekbarMin,seekbarMax;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
      setContentView(R.layout.searchbooth);
      submit=(Button)findViewById(R.id.submitBsearch);
		address=(EditText)findViewById(R.id.addressBsearch);
		tmin=(TextView)findViewById(R.id.seek1text);
		tmin.setText("");
		 sCitySector=(TextView)findViewById(R.id.sAddress);
		tmax=(TextView)findViewById(R.id.seek2text);tmax.setText("");
		seekbarMax=(SeekBar)findViewById(R.id.seekBar2);
		seekbarMin=(SeekBar)findViewById(R.id.seekBar1);
		seekbarMax.setMax(9999999);//set max value
		seekbarMin.setMax(999999);//set max value
		seekbarMax.setProgress(9999999);//set default at max
		seekbarMin.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			 int progressChanged =50000;
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
				
			
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				progressChanged = progress;
				tmin.setText(Integer.toString(progressChanged));
			}
		});
		
		seekbarMax.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			 int progressChanged =100000;
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				progressChanged = progress;
				tmax.setText(Integer.toString(progressChanged));
				
			}
		});
		
		address.setOnClickListener(new View.OnClickListener() {
		public void onClick(View arg0) {
				// TODO Auto-generated method stub
			address.setText("");	
			}
		});
		
		spinnerLocality=(Spinner)findViewById(R.id.spinnerLocalityBsearch);
		 spinnerLocality.setOnItemSelectedListener(this);
	       
		loadSpinnerLocality();
		displayListView();
		
submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sMin=seekbarMin.getProgress();
				sMax=seekbarMax.getProgress();
				sAddress=address.getText().toString();
				
			// address.setText("");
				
				if(sAddress.length()>0){
					Log.d("","displayAddress()");
					displayAddress();
				}
				else if(sCity=="All"){
				  Log.d("","displayAllCity();");
					displayAllCity();
				}
				else if(sSector.equals("All") && sCity!="All" && (sCity=="Chandigarh"|| sCity=="Mohali"|| sCity=="Panchkula")){
					displayAllSector();
					Log.d("","displayAllSector();");
				}else if(sCity!="All" && sSector!="All" && sSector!=null){
					displaySectorCity();
					Log.d("","displaySectorCity();");
				}
				
			}
		});
		
	
		
	}
	
	
 private void displayAllCity(){
		Log.d("displayAllCity()",sAddress+sMin+sMax+sSector);
		
		Cursor cursorAddress=db.getBoothCityAll(sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_CITY,
				db.KEY_SECTOR,
				db.KEY_CONDITION
			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerCity,
			R.id.shSellerSector,
			R.id.shSellerCondition
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_data, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListBsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchBooth.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateBooth.class);
		                     // sending data to new activity
		                     i.putExtra("adres", adres);
		                     startActivity(i);
		      			   finish();
		                    dialog.cancel();
		                }
		            });
		            builder1.setNegativeButton("Delete",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                   boolean a= db.Boothdelete(adres);
		                   Toast.makeText(getApplicationContext(), a+"", Toast.LENGTH_LONG).show();
		                   displayAllCity();
		                   
		                	dialog.cancel();
		                }
		                
		            });

		            AlertDialog alert11 = builder1.create();
		            alert11.show();
					return false;
				}
			
			});
	}
	
 private void displayAllSector(){
		Log.d("displayAllSector()",sCity+sMin+sMax+sSector);
		
		Cursor cursorAddress=db.getBoothSectorAll(sCity, sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_SECTOR,
				db.KEY_CITY,
				db.KEY_CONDITION
			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerSector,
			R.id.shSellerCity,
			R.id.shSellerCondition
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_data, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListBsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchBooth.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateBooth.class);
		                     // sending data to new activity
		                     i.putExtra("adres", adres);
		                     startActivity(i);
		      			   finish();
		                    dialog.cancel();
		                }
		            });
		            builder1.setNegativeButton("Delete",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                   boolean a= db.Boothdelete(adres);
		                   Toast.makeText(getApplicationContext(), a+"", Toast.LENGTH_LONG).show();
		                   displayAllSector();
		                	dialog.cancel();
		                }
		                
		            });

		            AlertDialog alert11 = builder1.create();
		            alert11.show();
					return false;
				}
			
			});
	}
	
	
private void displayAddress(){
		 Log.d("",sAddress);
			Cursor cursorAddress=db.getBoothAddress(sAddress);
			String[] columns = new String[] {
					db.KEY_NAME,
					db.KEY_NUMBER,
					db.KEY_PRICE,
					db.KEY_ADDRESS,
					db.KEY_SECTOR,
					db.KEY_CITY,
					db.KEY_CONDITION
				};
				
				// the XML defined views which the data will be bound to
				int[] to = new int[] { 
				R.id.shSellerName,
				R.id.shSellerNumber,
				R.id.shSellerPrice,
				R.id.shSellerAddress,
				R.id.shSellerSector,
				R.id.shSellerCity,
				R.id.shSellerCondition
				};
				
				// create the adapter using the cursor pointing to the desired data 
				//as well as the layout information
				SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.list_data, 
				cursorAddress, 
				columns, 
				to,
				0);
				
				ListView listView1 = (ListView) findViewById(R.id.searchListBsearch);
				
				// Assign adapter to ListView
				listView1.setAdapter(dataAdapter4);
				listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent, View view,
							int position, long id) {
						final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
						Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
						AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchBooth.this);
			            builder1.setMessage("Please select your option.");
			            builder1.setCancelable(true);
			            builder1.setPositiveButton("Modify",
			                    new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                	 Intent i = new Intent(getApplicationContext(), UpdateBooth.class);
			                     // sending data to new activity
			                     i.putExtra("adres", adres);
			                     startActivity(i);
			      			   finish();
			                    dialog.cancel();
			                }
			            });
			            builder1.setNegativeButton("Delete",
			                    new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                   boolean a= db.Boothdelete(adres);
			                   Toast.makeText(getApplicationContext(), a+"", Toast.LENGTH_LONG).show();
			                   displayAddress();
			                	dialog.cancel();
			                }
			                
			            });

			            AlertDialog alert11 = builder1.create();
			            alert11.show();
						return false;
					}
				
				});
				
				
		
	}
	

private void displaySectorCity(){
		Log.d("displaySectorCity()",sAddress+sMin+sMax+sSector);
		
		Cursor cursorAddress=db.getBoothCitySector(sCity, sSector, sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_SECTOR,
				db.KEY_CITY,
				db.KEY_CONDITION
			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerSector,
			R.id.shSellerCity,
			R.id.shSellerCondition
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_data, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListBsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchBooth.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateBooth.class);
		                     // sending data to new activity
		                     i.putExtra("adres", adres);
		                     startActivity(i);
		      			   finish();
		                    dialog.cancel();
		                }
		            });
		            builder1.setNegativeButton("Delete",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                   boolean a= db.Boothdelete(adres);
		                   Toast.makeText(getApplicationContext(), a+"", Toast.LENGTH_LONG).show();
		                   displaySectorCity();
		                	dialog.cancel();
		                }
		                
		            });

		            AlertDialog alert11 = builder1.create();
		            alert11.show();
					return false;
				}
			
			});
			
	}
	
	private void displayListView() {
		// TODO Auto-generated method stub
		
		Cursor cursor = db.getAllBooth();
		String[] columns = new String[] {
			db.KEY_NAME,
			db.KEY_NUMBER,
			db.KEY_PRICE,
			db.KEY_ADDRESS,
			db.KEY_SECTOR,
			db.KEY_CITY,
			db.KEY_CONDITION
		};
		
		// the XML defined views which the data will be bound to
		int[] to = new int[] { 
		R.id.shSellerName,
		R.id.shSellerNumber,
		R.id.shSellerPrice,
		R.id.shSellerAddress,
		R.id.shSellerSector,
		R.id.shSellerCity,
		R.id.shSellerCondition
		};
		
		// create the adapter using the cursor pointing to the desired data 
		//as well as the layout information
		SimpleCursorAdapter dataAdapter2 = new SimpleCursorAdapter(
		this, R.layout.list_data, 
		cursor, 
		columns, 
		to,
		0);
		
		ListView listView = (ListView) findViewById(R.id.searchListBsearch);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter2);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
				Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
				AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchBooth.this);
	            builder1.setMessage("Please select your option.");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Modify",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	 Intent i = new Intent(getApplicationContext(), UpdateBooth.class);
	                     // sending data to new activity
	                     i.putExtra("adres", adres);
	                     startActivity(i);
	      			   finish();
	                    dialog.cancel();
	                }
	            });
	            builder1.setNegativeButton("Delete",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                   boolean a= db.Boothdelete(adres);
	                   Toast.makeText(getApplicationContext(), a+"", Toast.LENGTH_LONG).show();
	                   displayListView();
	                	dialog.cancel();
	                }
	                
	            });

	            AlertDialog alert11 = builder1.create();
	            alert11.show();
				return false;
			}
		
		});
	}



private void loadSpinnerLocality() {
		// TODO Auto-generated method stub
		List<String> sectorLabel= new ArrayList<String>();
		sectorLabel.add("Select State");
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


	
public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item
	String label = parent.getItemAtPosition(position).toString();
	 sCity=label;
	if (m_intSpinnerInitiCount < NO_OF_EVENTS || sCity.equals("All")) {
        m_intSpinnerInitiCount++;
        sCitySector.setText("All");
        sCity="All";
    }else{
          AlertDialog.Builder builder = new AlertDialog.Builder(SearchBooth.this);
	   
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
	
public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}




}
