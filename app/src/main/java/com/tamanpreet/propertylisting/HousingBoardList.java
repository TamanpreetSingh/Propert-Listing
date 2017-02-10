package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class HousingBoardList extends Activity{
int master;
	String masters="8889";
String[] sectors;String[] cities ; String[] contact;
DatabaseHandler db;
ListView lw;
private static final String TAG = "CardListActivity";
private CardArrayAdapter cardArrayAdapter;
private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

	
	listView=(ListView)findViewById(R.id.card_listView);
	
	
	
	  listView.addHeaderView(new View(this));
      listView.addFooterView(new View(this));


      //Log.d("ok", "msg1");  
      
	db=new DatabaseHandler(getApplicationContext());
	ArrayList l1=(ArrayList)db.getHousingBoard();
	Iterator d=l1.iterator();
	String line1="",line2="",line3="",line4="";
	 sectors=new String[l1.size()];
	 cities=new String[l1.size()];
		contact=new String[l1.size()];
	int j=0;
	 cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
	while(d.hasNext())
	{
		
		AddHousingBoard object=(AddHousingBoard)d.next();
		line1="Contact Person: "+object.sName+"\nAddress: "
	            +object.sAddress;
		line2="\nCity: "+object.sCity+
		"\nCondition: "+object.sCondition+
		"\nSector: "+object.sSector+"\nFloor For Sale:"+object.sFloor+
		"\nFlat Category: "+object.sCategory+
		"\nAsking Price: "
	+object.sPrice+"\nContact Number: "
				+object.sNumber+"\n";
	sectors[j]=object.sSector;
	cities[j]=object.sCity;
		contact[j]=object.sNumber+"";
	
		j++;
	    //t1.setText(tt);
		Card card = new Card(line1,line2);
        cardArrayAdapter.add(card);
        
	}
	

   

   // for (int i = 0; i < l1.size(); i++) {
    //    Card card = new Card(line1,line2);
      //  cardArrayAdapter.add(card);
    //}
  
	
	listView.setAdapter(cardArrayAdapter);
	
	
	
	listView.setOnItemClickListener(new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			//Toast.makeText(getApplicationContext(),"hello"+arg2, 1000).show();
		
		master=arg2;
		
		PopupMenu pop=new PopupMenu(getApplicationContext(), arg1);
		
		//Log.d("menu", "menu");
		pop.getMenuInflater().inflate(R.menu.popup_menu,pop.getMenu());
		//Log.d("menu", "menu one");
		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				
				String str=(String)item.getTitle();
				Log.d("menu", "menu");
				if(str.equals("Show on Map"))
				{
					
					Intent intent=new Intent(Intent.ACTION_VIEW);
					String sec=sectors[master-1];
					String city=cities[master-1];
					String lat="",lon="";
				Log.d("hello",""+city);
				Log.d("hello",""+sec);
if(city.equals("Chandigarh")){
					
					
					if(sec.equals("sector 4"))
					{
						
						lat="30.7516605";
						lon="76.8015764";
					Log.d("print", "maps");
					}
					
					if(sec.equals("sector 6"))
					{
						
						lat="30.737101";
						lon="76.8156364";
					}
					
					if(sec.equals("sector 1"))
					{
						
						lat="30.7532689";
						lon="76.812601";
					}
				
				
					if(sec.equals("sector 2"))
					{
						//30.7634375,76.791556
						lat="30.7634375";
						lon="76.791556";
					}
					if(sec.equals("sector 15"))
					{
						//30.7523136, 76.772745

						lat="30.7523136";
						lon="76.772745";
					}
					if(sec.equals("sector 16"))
					{
						//30.7463075,76.7773531

						lat="30.7463075";
						lon="76.7773531";
					}
				
					if(sec.equals("sector 8"))
					{
						//30.7412231,76.7988434

						lat="30.7412231";
						lon="76.7988434";
					}
				}
				
				
			
				if(city.equals("Panchkula"))
				{
					
					if(sec.equals("sector 2"))
					{
						//30.6964736,76.865268

						lat="30.7412231";
						lon="76.7988434";
					}
					
					if(sec.equals("sector 4"))
					{
						//30.6891135,76.860718

						lat="30.6891135";
						lon="76.860718";
					}
					
					if(sec.equals("sector 5"))
					{
						//30.696786,76.8560464

						lat="30.696786";
						lon="76.8560464";
					}
					
				}
				
				
				
				if(city.equals("Mohali"))
				{
					
					if(sec.equals("sector 66"))
					{
						//30.675045,76.7345575

						lat="30.675045";
						lon="76.7345575";
					}
					
					if(sec.equals("sector 67"))
					{
						//30.6802544,76.7296916

						lat="30.6802544";
						lon="76.7296916";
					}
					
					if(sec.equals("sector 68"))
					{
						//30.6855852,76.7243565

						lat="30.6855852";
						lon="76.7243565";
					}
					
				}



					intent.setData(Uri.parse("http://maps.google.com/?q="+lat+","+lon));
					startActivity(intent);



				}
				
				if(str.equals("Call"))
				{
					new AlertDialog.Builder(HousingBoardList.this)
							.setTitle("Are you sure you want to call ?")
							.setInverseBackgroundForced(true)
							.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									call();
								}
							})
							.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							})
							.create().show();




				}
				
				if(str.equals("Message"))
				{
					new AlertDialog.Builder(HousingBoardList.this)
							.setTitle("Are you sure you want to message ?")
							.setMessage("contact me I am interested in your property")
							.setInverseBackgroundForced(true)
							.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									message();
								}
							})
							.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							})
							.create().show();

				}


				return true;			
			}
		});
		
		pop.show();
		
		
		}		
	});
	//registerForContextMenu(listView);	
	//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
	//setListAdapter(adapter);	
	}
	public void call()
	{
		try {
			masters = contact[master - 1];

			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + masters));
			startActivity(callIntent);


		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(getApplicationContext(), "mobile no not valid", 1000).show();
		}
	}
	public void message()
	{
		try {
			android.telephony.SmsManager t = android.telephony.SmsManager.getDefault();
			//t.sendTextMessage(destinationAddress, scAddress, text, sentIntent, deliveryIntent)
			masters=contact[master-1];

			t.sendTextMessage(masters+"", null, "contact me I am interested in your property", null, null);

			Toast.makeText(HousingBoardList.this, "Your sms has successfully sent to "+master,1000).show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	
	
	/*public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo)
	{
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select Action");
		menu.add(0, v.getId(), 0, "Show on Map");
		menu.add(0, v.getId(), 0, "Call");
		menu.add(0, v.getId(), 0, "Message");
	}



	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getTitle()=="Show on Map"){
			
			//Intent intent=new Intent(Intent.ACTION_VIEW);
			//intent.setData(Uri.parse());
		}
		
if(item.getTitle()=="Call"){
			
			int k=0;
			
			//String t =(String) this.getParent().getTitle();
			//k=listView.get;
			Toast.makeText(getApplicationContext(), "id= "+master ,Toast.LENGTH_LONG).show();   
			//Intent intent=new Intent(Intent.ACTION_VIEW);
			//intent.setData(Uri.parse());
		}

if(item.getTitle()=="Message"){
	
	int k=0;
	k=listView.getSelectedItemPosition();
	//k=listView.get;
	Toast.makeText(getApplicationContext(), "id= "+k, Toast.LENGTH_LONG).show();   
	//Intent intent=new Intent(Intent.ACTION_VIEW);
	//intent.setData(Uri.parse());
}
		
		return true;
	}


/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	
	String item=(String)getListAdapter().getItem(position);
	int sec=Integer.parseInt(item);
	Toast.makeText(getApplicationContext(),"SEC  "+sec, 1000).show();
	}

*/






}}
