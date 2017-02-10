package com.tamanpreet.propertylisting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SearchSociety  extends Activity  implements OnItemSelectedListener  {

	Spinner spinnerType,spinnerLocality;
	Button submit;
	TextView tmin,tmax;
	TextView sCitySector;

	EditText address,society;
	DatabaseHandler db=new DatabaseHandler(this);
    ArrayList<HashMap<String, String>> usersList=  new ArrayList<HashMap<String, String>>();
	
    String sSector,sCity,sAddress,sSociety,s;
    double sMin=0.0,sMax=999999999;  
    boolean bmin=false,bmax=false,btype=false,blocality=false;
	SeekBar seekbarMin,seekbarMax;
	private int m_intSpinnerInitiCount = 0;
	private static final int NO_OF_EVENTS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seacrhsociety);
		submit=(Button)findViewById(R.id.submitSsearch);
		address=(EditText)findViewById(R.id.addressSsearch);
		society=(EditText)findViewById(R.id.societySsearch);
        sCitySector=(TextView)findViewById(R.id.sAddress);

		tmin=(TextView)findViewById(R.id.seek1text);
		tmin.setText("");
		tmax=(TextView)findViewById(R.id.seek2text);tmax.setText("");
		seekbarMax=(SeekBar)findViewById(R.id.seekBarS22);
		seekbarMin=(SeekBar)findViewById(R.id.seekBarS11);
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
		society.setOnClickListener(new View.OnClickListener() {
		public void onClick(View arg0) {
				// TODO Auto-generated method stub
			society.setText("");	
			}
		});
		spinnerLocality=(Spinner)findViewById(R.id.spinnerLocalitySSearch1);
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
				sSociety=society.getText().toString();
				Toast.makeText(getApplicationContext(),sAddress+"sector "+sSector+"city "+sCity+sMin+"Max Amount:"+sMax, 
						10000).show();
			// listView.removeAllViewsInLayout();
			// address.setText("");
				
				if(sAddress.length()>0){
					Log.d("","displayAddress()");
					displayAddress();
				}else if(sSociety.length()>0){
					Log.d("","displayAddress()");
					displaySociety();
				}
				else if(sCity=="All"){
				  Log.d("","displayAllCity();");
					displayAllCity();
				}
				else if(sSector.equals("All")){
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
		
		Cursor cursorAddress=db.getSocietyCityAll(sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_SECTOR,
				db.KEY_CITY,
				db.KEY_CATEGORY,
				db.KEY_FLOOR,
				db.KEY_CONDITION,
				db.KEY_SOCIETY
			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerSector,
			R.id.shSellerCity,
			R.id.shSellerCategory,
			R.id.shSellerFloor,
			R.id.shSellerCondition,
			R.id.shSellerSociety
			
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_datasociety, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListSsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
		                   boolean a= db.Societydelete(adres);
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
		
		Cursor cursorAddress=db.getSocietySectorAll(sCity, sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_CITY,
				db.KEY_SECTOR,
				db.KEY_CATEGORY,
				db.KEY_FLOOR,
				db.KEY_CONDITION,
				db.KEY_SOCIETY

			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerSector,
			R.id.shSellerCity,
			R.id.shSellerCategory,
			R.id.shSellerFloor,
			R.id.shSellerCondition,
			R.id.shSellerSociety
			
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_datasociety, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListSsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
		                   boolean a= db.Societydelete(adres);
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
			Cursor cursorAddress=db.getSocietyAddress(sAddress);
			String[] columns = new String[] {
					db.KEY_NAME,
					db.KEY_NUMBER,
					db.KEY_PRICE,
					db.KEY_ADDRESS,
					db.KEY_CITY,
					db.KEY_SECTOR,
					db.KEY_CATEGORY,
					db.KEY_FLOOR,
					db.KEY_CONDITION,
					db.KEY_SOCIETY

				};
				
				// the XML defined views which the data will be bound to
				int[] to = new int[] { 
				R.id.shSellerName,
				R.id.shSellerNumber,
				R.id.shSellerPrice,
				R.id.shSellerAddress,
				R.id.shSellerSector,
				R.id.shSellerCity,
				R.id.shSellerCategory,
				R.id.shSellerFloor,
				R.id.shSellerCondition,
				R.id.shSellerSociety
				
				};
				
				// create the adapter using the cursor pointing to the desired data 
				//as well as the layout information
				SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.list_datasociety, 
				cursorAddress, 
				columns, 
				to,
				0);
				
				ListView listView1 = (ListView) findViewById(R.id.searchListSsearch);
				
				// Assign adapter to ListView
				listView1.setAdapter(dataAdapter4);
				listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent, View view,
							int position, long id) {
						final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
						Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
						AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
			            builder1.setMessage("Please select your option.");
			            builder1.setCancelable(true);
			            builder1.setPositiveButton("Modify",
			                    new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                	Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
			                   boolean a= db.Societydelete(adres);
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
	
	private void displaySociety(){
		 Log.d("",sAddress);
			Cursor cursorAddress=db.getSocietyName(sSociety);
			String[] columns = new String[] {
					db.KEY_NAME,
					db.KEY_NUMBER,
					db.KEY_PRICE,
					db.KEY_ADDRESS,
					db.KEY_CITY,
					db.KEY_SECTOR,
					db.KEY_CATEGORY,
					db.KEY_FLOOR,
					db.KEY_CONDITION,
					db.KEY_SOCIETY

				};
				
				// the XML defined views which the data will be bound to
				int[] to = new int[] { 
				R.id.shSellerName,
				R.id.shSellerNumber,
				R.id.shSellerPrice,
				R.id.shSellerAddress,
				R.id.shSellerSector,
				R.id.shSellerCity,
				R.id.shSellerCategory,
				R.id.shSellerFloor,
				R.id.shSellerCondition,
				R.id.shSellerSociety
				
				};
				
				// create the adapter using the cursor pointing to the desired data 
				//as well as the layout information
				SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.list_datasociety, 
				cursorAddress, 
				columns, 
				to,
				0);
				
				ListView listView1 = (ListView) findViewById(R.id.searchListSsearch);
				
				// Assign adapter to ListView
				listView1.setAdapter(dataAdapter4);
				listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent, View view,
							int position, long id) {
						final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
						Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
						AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
			            builder1.setMessage("Please select your option.");
			            builder1.setCancelable(true);
			            builder1.setPositiveButton("Modify",
			                    new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int id) {
			                	Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
			                   boolean a= db.Societydelete(adres);
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
		Log.d("displaySectorCity()",sAddress+sMin+sMax+sSector+sCity);
		
		Cursor cursorAddress=db.getSocietyCitySector(sCity, sSector, sMax, sMin);
		String[] columns = new String[] {
				db.KEY_NAME,
				db.KEY_NUMBER,
				db.KEY_PRICE,
				db.KEY_ADDRESS,
				db.KEY_CITY,
				db.KEY_SECTOR,
				db.KEY_CATEGORY,
				db.KEY_FLOOR,
				db.KEY_CONDITION,
				db.KEY_SOCIETY

			};
			
			// the XML defined views which the data will be bound to
			int[] to = new int[] { 
			R.id.shSellerName,
			R.id.shSellerNumber,
			R.id.shSellerPrice,
			R.id.shSellerAddress,
			R.id.shSellerSector,
			R.id.shSellerCity,
			R.id.shSellerCategory,
			R.id.shSellerFloor,
			R.id.shSellerCondition,
			R.id.shSellerSociety
			
			};
			
			// create the adapter using the cursor pointing to the desired data 
			//as well as the layout information
			SimpleCursorAdapter dataAdapter4 = new SimpleCursorAdapter(
			getApplicationContext(), R.layout.list_datasociety, 
			cursorAddress, 
			columns, 
			to,
			0);
			
			ListView listView1 = (ListView) findViewById(R.id.searchListSsearch);
			
			// Assign adapter to ListView
			listView1.setAdapter(dataAdapter4);
			listView1.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
					Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
		            builder1.setMessage("Please select your option.");
		            builder1.setCancelable(true);
		            builder1.setPositiveButton("Modify",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                	 Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
		                   boolean a= db.Societydelete(adres);
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
		
		Cursor cursor = db.getAllSociety();
		String[] columns = new String[] {
			db.KEY_NAME,
			db.KEY_NUMBER,
			db.KEY_PRICE,
			db.KEY_ADDRESS,
			db.KEY_CITY,
			db.KEY_SECTOR,
			db.KEY_CATEGORY,
			db.KEY_FLOOR,
			db.KEY_CONDITION,
			db.KEY_SOCIETY

		};
		
		// the XML defined views which the data will be bound to
		int[] to = new int[] { 
		R.id.shSellerName,
		R.id.shSellerNumber,
		R.id.shSellerPrice,
		R.id.shSellerAddress,
		R.id.shSellerSector,
		R.id.shSellerCity,
		R.id.shSellerCategory,
		R.id.shSellerFloor,
		R.id.shSellerCondition,
		R.id.shSellerSociety
		
		};
		
		// create the adapter using the cursor pointing to the desired data 
		//as well as the layout information
		SimpleCursorAdapter dataAdapter2 = new SimpleCursorAdapter(
		this, R.layout.list_datasociety, 
		cursor, 
		columns, 
		to,
		0);
		
		ListView listView = (ListView) findViewById(R.id.searchListSsearch);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter2);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				final String adres=((TextView) view.findViewById(R.id.shSellerAddress)).getText().toString().trim();
				Toast.makeText(getApplicationContext(), adres, Toast.LENGTH_LONG).show();
				AlertDialog.Builder builder1 = new AlertDialog.Builder(SearchSociety.this);
	            builder1.setMessage("Please select your option.");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Modify",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	 Intent i = new Intent(getApplicationContext(), UpdateSociety.class);
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
	                   boolean a= db.Societydelete(adres);
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



	public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
		 // On selecting a spinner item

		String label = parent.getItemAtPosition(position).toString();
	    sCity=label;
	  
		if (m_intSpinnerInitiCount < NO_OF_EVENTS || sCity.equals("All")) {
	        m_intSpinnerInitiCount++;
	        sCitySector.setText("All");
	        sCity="All";
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




@Override
public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
}


}
