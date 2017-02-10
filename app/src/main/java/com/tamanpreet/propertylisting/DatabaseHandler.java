package com.tamanpreet.propertylisting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final String KEY_DATABASE="_propertylistingdatabase";
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="_sellername";
    public static final String KEY_ADDRESS="_address";
    public static final String KEY_NUMBER="_phone";
    public static final String KEY_PRICE="_price";
    public static final String KEY_TYPE="_type";
    public static final String KEY_CITY="_city";
    public static final String KEY_SECTOR="_sector";
    public static final String KEY_CONDITION="_condition";
    public static final String KEY_CATEGORY="_category";
    public static final String KEY_FLOOR="_floor";
    public static final String KEY_FORSALE="_forsale";
    public static final String KEY_BASEMENT="_basement";
    public static final String KEY_STOREY="_storey";
    
    static final String KEY_SOCIETY="_society";
    private static final String TABLE_HB="_hb";
    private static final String TABLE_SOCIETY="_society";
    private static final String TABLE_KOTHI="_kothi";
    private static final String TABLE_SCO="_sco";
    private static final String TABLE_BOOTH="_booth";
    private static final String TABLE_CHD="_chandigarh";
    private static final String TABLE_MOHALI="_mohali";
    private static final String TABLE_PKL="_panchkula";
   
	private static final int DATABASE_VERSION= 1;
	public String DB_PATH = "data/data/com.example.propertlisting/database/";
	

	public DatabaseHandler(Context context) {
		super(context, KEY_DATABASE,null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		
		Log.d("database",1+"");
		
		//	SQLiteDatabase b=this.getWritableDatabase();
	
		//b.execSQL("CREATE TABLE signup(fullname TEXT,userid TEXT PRIMARY KEY,password TEXT,retype TEXT,dob TEXT,mobileno TEXT)");
		
		//Log.d("table","created");
		
	}

	@Override
public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("CREATE TABLE _type(_id INTEGER PRIMARY KEY AUTOINCREMENT, _kind VARCHAR);");
	Log.d("database",2+"");
	   
	   Log.d("database",2.1+""); 
	   db.execSQL("CREATE TABLE _chandigarh(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sector VARCHAR)");
	   Log.d("database",2.2+"");
		db.execSQL("CREATE TABLE _mohali(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sector VARCHAR)");
		db.execSQL("CREATE TABLE _panchkula(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sector VARCHAR)");
		db.execSQL("CREATE TABLE signup(fullname TEXT,userid TEXT PRIMARY KEY,password TEXT,retype TEXT,dob TEXT,mobileno TEXT)");
		
		db.execSQL("insert into _chandigarh(_sector) values('All')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 1')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 2')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 3')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 4')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 5')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 6')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 7')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 8')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 9')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 10')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 11')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 12')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 14')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 15')");
		db.execSQL("insert into _chandigarh(_sector) values('sector 16')");
		
		db.execSQL("insert into _mohali(_sector) values('All')");
		db.execSQL("insert into _mohali(_sector) values('sector 66')");
		db.execSQL("insert into _mohali(_sector) values('sector 67')");
		db.execSQL("insert into _mohali(_sector) values('sector 68')");
		db.execSQL("insert into _mohali(_sector) values('sector 69')");
		db.execSQL("insert into _mohali(_sector) values('sector 70')");
		db.execSQL("insert into _mohali(_sector) values('sector 71')");
		db.execSQL("insert into _mohali(_sector) values('sector 72')");
		db.execSQL("insert into _mohali(_sector) values('sector 73')");
		
		Log.d("bef","pkl");
		db.execSQL("insert into _panchkula(_sector) values('All')");
		Log.d("aft","pkl");
		db.execSQL("insert into _panchkula(_sector) values('sector 1')");
		db.execSQL("insert into _panchkula(_sector) values('sector 2')");
		db.execSQL("insert into _panchkula(_sector) values('sector 3')");
		db.execSQL("insert into _panchkula(_sector) values('sector 4')");
		db.execSQL("insert into _panchkula(_sector) values('sector 5')");
		db.execSQL("insert into _panchkula(_sector) values('sector 6')");
		db.execSQL("insert into _panchkula(_sector) values('sector 7')");
		db.execSQL("insert into _panchkula(_sector) values('sector 8')");
		db.execSQL("insert into _panchkula(_sector) values('sector 9')");
		
		db.execSQL("CREATE TABLE _kothi(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sellername VARCHAR," +
				" _phone VARCHAR, _address VARCHAR, _city VARCHAR,_sector VARCHAR, _condition VARCHAR, _price INTEGER," +
				" _storey VARCHAR, _basement VARCHAR, _forsale VARCHAR)");
		
		db.execSQL("CREATE TABLE _sco(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sellername VARCHAR," +
				" _phone VARCHAR, _address VARCHAR, _city VARCHAR,_sector VARCHAR, _condition VARCHAR, _price INTEGER," +
				" _storey VARCHAR, _basement VARCHAR, _forsale VARCHAR)");
		
		db.execSQL("CREATE TABLE _hb(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sellername VARCHAR," +
				" _phone VARCHAR, _address VARCHAR, _city VARCHAR,_sector VARCHAR, _condition VARCHAR, _price INTEGER," +
				" _floor VARCHAR, _category VARCHAR)");

		db.execSQL("CREATE TABLE _society(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sellername VARCHAR," +
				" _phone VARCHAR,_society VARCHAR,_address VARCHAR, _city VARCHAR,_sector VARCHAR, _condition VARCHAR, _price INTEGER," +
				" _floor VARCHAR, _category VARCHAR)");

		db.execSQL("CREATE TABLE _booth(_id INTEGER PRIMARY KEY AUTOINCREMENT, _sellername VARCHAR," +
				" _phone VARCHAR, _address VARCHAR, _city VARCHAR,_sector VARCHAR, _condition VARCHAR, _price INTEGER)");
		;
		Log.d("database",3+"");

}

	
	
	
	long insert123(String fullname, String userid, String password, String retype, String dob, String mobileno)
	{
		
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		
		cv.put("fullname",fullname);
		cv.put("userid",userid);
		cv.put("password",password);
		cv.put("retype",retype);
		cv.put("dob",dob);
		cv.put("mobileno",mobileno);
		
		long n=db.insert("signup", null, cv);
		return n;
	}
			
	   // Getting All Contacts
   boolean select_signup(String userid, String password)
   {
	   Log.d("bb","cc");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from signup where userid='"+userid+"' and password='"+password+"'",null );
 
        // looping through all rows and adding to list
        if (cursor.getCount()!=0)
        		return true;
        
        else
        return false;
    }
   
   public Boolean updatepassword(String userid, String old, String password, String retype)
	 { SQLiteDatabase db = this.getReadableDatabase();
		
		 ContentValues cv=new ContentValues();
		
		 cv.put("password", password);
		 cv.put("retype",retype);
		    db.update("signup", cv, "userid='"+userid+"'", null);
			return null;
	 }
  
   public Boolean editprofile(String userid, String fullname, String dob, String mobileno)
   {
	   SQLiteDatabase db=this.getReadableDatabase();
	   
	   ContentValues cv=new ContentValues();
	   
	   cv.put("fullname", fullname);
	   cv.put("dob", dob);
	   cv.put("mobileno",mobileno);
	   
	return null;
	   
	   
   }

	
	
	
	
	
	
	
	
	
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
	Log.d("database",4+"");
	 db = this.getWritableDatabase();
	    db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOOTH);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_HB);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_KOTHI);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCO);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHD);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_MOHALI);
	    db.execSQL("DROP TABLE IF EXISTS "+TABLE_PKL);
	    db.execSQL("DROP TABLE IF EXISTS "+TABLE_SOCIETY);
	    onCreate(db);
	    ;
	    Log.d("database",5+"");
	}
	
public boolean newHb(String sellername,String address,String city,String sector,String number, double price,String condition,
		String floor,String category)
    {Log.d("database",6+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_CATEGORY, category); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FLOOR,floor); // type
     values.put(KEY_SECTOR ,sector); // type
      db.insert(TABLE_HB, null, values);
      ;
    // Closing database connection
      Log.d("database",7+"");
      return true;
	
}	

public boolean newSociety(String sellername,String address,String society,String city,String sector,String number, double price,String condition,
		String floor,String category)
    {Log.d("database",8+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_CATEGORY, category); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FLOOR,floor); // type
     values.put(KEY_SECTOR ,sector); // type
     values.put(KEY_SOCIETY,society); // type
     db.insert(TABLE_SOCIETY, null, values);
         // Closing database connection
     ;
     Log.d("database",9+"");
     return true;
}	

public boolean newKothi(String sellername,String address,String city, String sector,String number, double price,String condition,
		String storey,String basement,String forsale)
    {Log.d("database",10+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_BASEMENT, basement); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FORSALE,forsale); // type
     values.put(KEY_STOREY ,storey); // type
     values.put(KEY_SECTOR ,sector); // type
     db.insert(TABLE_KOTHI,null, values);
      // Closing database connection
     ;
     Log.d("database",11+"");
     return true;
}

public boolean newSco(String sellername,String address,String city, String sector,String number, double price,String condition,
		String storey,String basement,String forsale)
    {Log.d("database",12+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_BASEMENT, basement); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FORSALE,forsale); // type
     values.put(KEY_STOREY ,storey); // type
     values.put(KEY_SECTOR ,sector); // type
     db.insert(TABLE_SCO,null, values);
        // Closing database connection
     ;
     Log.d("database",13+"");
     return true;
    }	

public boolean newBooth(String sellername,String address,String city, String sector,String number, double price,String condition)
    {Log.d("database",14+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_SECTOR, sector);
     db.insert(TABLE_BOOTH, null, values);
         // Closing database connection
     ;
     Log.d("database",15+"");
     return true;

}


//1
public Cursor getAllSociety() {
	Log.d("database",31+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society",null);
	 
	   Log.d("database",32+"");
	   return cursor;
	   }
//2
public Cursor getSocietyCitySector(String city,String sector,double max,double min) {
	Log.d("database",31+"getSocietyCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _city='"+city+"' and _sector='"+sector+"' and _price " +
	   		"between "+min+" and "+max,null);
	   Log.d("database",32+"getSocietyCitySectorRange");
	   return cursor;
	   }
//3
public Cursor getSocietyCityAll(double max,double min) {
	Log.d("database",31+"getSocietyCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _price between "+min+" and "+max,null);
	   Log.d("database",32+"getSocietyCitySectorRange");
	   return cursor;
	   }
//4
public Cursor getSocietySectorAll(String city,double max,double min) {
	Log.d("database",31+"getSocietyRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _city= '"+city+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getSocietyRange");
	   return cursor;
	   }
//5
public Cursor getSocietyAddress(String address) {
	Log.d("database",31+"getSocietyAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _address like'%"+address+"%'",null);
	   Log.d("database",32+"getSocietyAddress");
	   return cursor;
	   }

public Cursor getSocietyName(String society) {
	Log.d("database",31+"getSocietyAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _society like'%"+society+"%'",null);
	   Log.d("database",32+"getSocietyAddress");
	   return cursor;
	   }

public Cursor getSocietyAddressFull(String address) {
	Log.d("database",31+"getSocietyAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _society where _address ='"+address+"'",null);
	   Log.d("database",32+"getSocietyAddress");
	   return cursor;
	   }
public boolean Societydelete(String address){
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.delete(TABLE_SOCIETY, KEY_ADDRESS+"='"+address+"'", null);
	  //db.rawQuery("delete from _booth where_address ='"+address+"'", null);
	   Log.d("database",32+"deletesco");
	   return true;
	}





//1
public Cursor getAllHb() {
	Log.d("database",31+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb",null);
	   Log.d("database",32+"");
	   return cursor;
	   }
//2
public Cursor getHbCitySector(String city,String sector,double max,double min) {
	Log.d("database",31+"getHbCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb where _city='"+city+"' and _sector='"+sector+"' and _price " +
	   		"between "+min+" and "+max,null);
	   Log.d("database",32+"getHbCitySectorRange");
	   return cursor;
	   }
//3
public Cursor getHbCityAll(double max,double min) {
	Log.d("database",31+"getHbCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb where _price between "+min+" and "+max,null);
	   Log.d("database",32+"getHbCitySectorRange");
	   return cursor;
	   }
//4
public Cursor getHbSectorAll(String city,double max,double min) {
	Log.d("database",31+"getHbRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb where _city= '"+city+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getHbRange");
	   return cursor;
	   }
//5
public Cursor getHbAddress(String address) {
	Log.d("database",31+"getHbAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb where _address like'%"+address+"%'",null);
	   Log.d("database",32+"getHbAddress");
	   return cursor;
	   }

public Cursor getHbAddressFull(String address) {
	Log.d("database",31+"getHbAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _hb where _address ='"+address+"'",null);
	   Log.d("database",32+"getHbAddress");
	   return cursor;
	   }
public boolean Hbdelete(String address){
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.delete(TABLE_HB, KEY_ADDRESS+"='"+address+"'", null);
	  //db.rawQuery("delete from _booth where_address ='"+address+"'", null);
	   Log.d("database",32+"deletehb");
	   return true;
	}




//1
public Cursor getAllSco() {
	Log.d("database",31+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco",null);
	   Log.d("database",32+"");
	   return cursor;
	   }
//2
public Cursor getScoCitySector(String city,String sector,double max,double min) {
	Log.d("database",31+"getScoCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco where _city='"+city+"' and _sector='"+sector+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getScoCitySectorRange");
	   return cursor;
	   }
//3
public Cursor getScoCityAll(double max,double min) {
	Log.d("database",31+"getScoCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco where _price between "+min+" and "+max,null);
	   Log.d("database",32+"getScoCitySectorRange");
	   return cursor;
	   }
//4
public Cursor getScoSectorAll(String city,double max,double min) {
	Log.d("database",31+"getScoRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco where _city= '"+city+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getScoRange");
	   return cursor;
	   }
//5
public Cursor getScoAddress(String address) {
	Log.d("database",31+"getScoAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco where _address like'%"+address+"%'",null);
	   Log.d("database",32+"getScoAddress");
	   return cursor;
	   }
public Cursor getScoAddressFull(String address) {
	Log.d("database",31+"getScoAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _sco where _address ='"+address+"'",null);
	   Log.d("database",32+"getScoAddress");
	   return cursor;
	   }
public boolean Scodelete(String address){
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.delete(TABLE_SCO, KEY_ADDRESS+"='"+address+"'", null);
	  //db.rawQuery("delete from _booth where_address ='"+address+"'", null);
	   Log.d("database",32+"deletesco");
	   return true;
	}




      public List<AddBooth> getBooths(){
	
	List<AddBooth> recordlist=new ArrayList<AddBooth>();
	String sql="SELECT * FROM _booth";
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery(sql, null);
	
	
	if(cursor.moveToFirst()){
		Log.d("kahani",""+cursor.getCount());
		Log.d("done",""+cursor.getColumnCount());
		
		do{
			Log.d("kahani",""+cursor.getCount());
			Log.d("done",""+cursor.getColumnCount());
		String pnumber=cursor.getString(2);
			Log.d("number",""+pnumber);
			//int number=Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_NUMBER")));
			double price=Integer.parseInt(cursor.getString(7));
		String address=cursor.getString(3);
			String name=cursor.getString(1);
			String condition=cursor.getString(6);
			String city=cursor.getString(4);
			String sector=cursor.getString(5);
		AddBooth object=new AddBooth();	
		object.sNumber=pnumber;
		object.sName=name;
		object.sPrice=price;
		object.sAddress=address;
		object.sCondition=condition;
		object.sCity=city;
		object.sSector=sector;
		recordlist.add(object);
		
		}while(cursor.moveToNext());
	}
	cursor.close();
	db.close();
	return recordlist;


}







      public List<AddSociety> getSocities(){
	
	List<AddSociety> recordlist=new ArrayList<AddSociety>();
	String sql="SELECT * FROM _society";
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery(sql, null);
	
	
	if(cursor.moveToFirst()){
		Log.d("kahani",""+cursor.getCount());
		Log.d("done",""+cursor.getColumnCount());
		
		do{
			Log.d("kahani",""+cursor.getCount());
			Log.d("done",""+cursor.getColumnCount());
		String pnumber=cursor.getString(2);
			Log.d("number",""+pnumber);
			//int number=Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_NUMBER")));
			double price=Integer.parseInt(cursor.getString(8));
		String address=cursor.getString(3);
			String name=cursor.getString(1);
			String condition=cursor.getString(7);
			String city=cursor.getString(5);
			String societyName=cursor.getString(4);
			String sector=cursor.getString(6);
			String floor=cursor.getString(9);
		AddSociety object=new AddSociety();	
		object.sNumber=pnumber;
		object.sName=name;
		object.sPrice=price;
		object.sAddress=address;
		object.sCondition=condition;
		object.sCity=city;
		object.sSector=sector;
		object.sFloor=floor;
		recordlist.add(object);
		
		}while(cursor.moveToNext());
	}
	cursor.close();
	db.close();
	return recordlist;


}


      public List<AddKothi> getkothis(){
    		
    		List<AddKothi> recordlist=new ArrayList<AddKothi>();
    		String sql="SELECT * FROM _kothi";
    		SQLiteDatabase db=this.getReadableDatabase();
    		Cursor cursor=db.rawQuery(sql, null);
    		
    		
    		if(cursor.moveToFirst()){
    			Log.d("kahani",""+cursor.getCount());
    			Log.d("done",""+cursor.getColumnCount());
    			
    			do{
    				Log.d("kahani",""+cursor.getCount());
    				Log.d("done",""+cursor.getColumnCount());
    			String pnumber=cursor.getString(2);
    				Log.d("number",""+pnumber);
    				//int number=Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_NUMBER")));
    				double price=Integer.parseInt(cursor.getString(7));
    			String address=cursor.getString(3);
    				String name=cursor.getString(1);
    				String condition=cursor.getString(6);
    				String city=cursor.getString(4);
    				String storey=cursor.getString(8);
    				String sector=cursor.getString(5);
    				String forsale=cursor.getString(10);
    				String base=cursor.getString(10);
    				if(base!=null){base="yes";}else{base="no";}
    			
    				
    				AddKothi object=new AddKothi();	
    			object.sNumber=pnumber;
    			object.sName=name;
    			object.sPrice=price;
    			object.sAddress=address;
    			object.sCondition=condition;
    			object.sCity=city;
    			object.sSector=sector;
    			object.sStorey=storey;
    			object.sForSale=forsale;
    			object.sBasement=base;
    			    			recordlist.add(object);
    			
    			}while(cursor.moveToNext());
    		}
    		cursor.close();
    		db.close();
    		return recordlist;


    	}     
      
      
      
      
      public List<AddHousingBoard> getHousingBoard(){
  		
  		List<AddHousingBoard> recordlist=new ArrayList<AddHousingBoard>();
  		String sql="SELECT * FROM _hb";
  		SQLiteDatabase db=this.getReadableDatabase();
  		Cursor cursor=db.rawQuery(sql, null);
  		
  		
  		if(cursor.moveToFirst()){
  			Log.d("kahani",""+cursor.getCount());
  			Log.d("done",""+cursor.getColumnCount());
  			
  			do{
  				Log.d("kahani",""+cursor.getCount());
  				Log.d("done",""+cursor.getColumnCount());
  			String pnumber=cursor.getString(2);
  				Log.d("number",""+pnumber);
  				//int number=Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_NUMBER")));
  				double price=Integer.parseInt(cursor.getString(7));
  			String address=cursor.getString(3);
  				String name=cursor.getString(1);
  				String condition=cursor.getString(6);
  				String city=cursor.getString(4);
  				String floor=cursor.getString(8);
  				String sector=cursor.getString(5);
  				String category=cursor.getString(9);
  								
  				AddHousingBoard object=new AddHousingBoard();	
  			object.sNumber=pnumber;
  			object.sName=name;
  			object.sPrice=price;
  			object.sAddress=address;
  			object.sCondition=condition;
  			object.sCity=city;
  			object.sSector=sector;
  			object.sFloor=floor;
  			object.sCategory=category;
  			    			recordlist.add(object);
  			
  			}while(cursor.moveToNext());
  		}
  		cursor.close();
  		db.close();
  		return recordlist;


  	}     
      
      
      
      
      
      
      public List<AddSco> getSco(){
    		
    		List<AddSco> recordlist=new ArrayList<AddSco>();
    		String sql="SELECT * FROM _sco";
    		SQLiteDatabase db=this.getReadableDatabase();
    		Cursor cursor=db.rawQuery(sql, null);
    		
    		
    		if(cursor.moveToFirst()){
    			Log.d("kahani",""+cursor.getCount());
    			Log.d("done",""+cursor.getColumnCount());
    			
    			do{
    				Log.d("kahani",""+cursor.getCount());
    				Log.d("done",""+cursor.getColumnCount());
    			String pnumber=cursor.getString(2);
    				Log.d("number",""+pnumber);
    				//int number=Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_NUMBER")));
    				double price=Integer.parseInt(cursor.getString(7));
    			String address=cursor.getString(3);
    				String name=cursor.getString(1);
    				String condition=cursor.getString(6);
    				String city=cursor.getString(4);
    				String storey=cursor.getString(8);
    				String sector=cursor.getString(5);
    				String forsale=cursor.getString(10);
    				String base=cursor.getString(10);
    				if(base!=null){base="yes";}else{base="no";}
    			
    				
    				AddSco object=new AddSco();	
    			object.sNumber=pnumber;
    			object.sName=name;
    			object.sPrice=price;
    			object.sAddress=address;
    			object.sCondition=condition;
    			object.sCity=city;
    			object.sSector=sector;
    			object.sStorey=storey;
    			object.sForSale=forsale;
    			object.sBasement=base;
    			    			recordlist.add(object);
    			
    			}while(cursor.moveToNext());
    		}
    		cursor.close();
    		db.close();
    		return recordlist;


    	}     
      
      
      
      
      
      
      
public String forgotPassword(String user)
{
	
	String sql="SELECT * FROM signup where userid='"+user+"'";
	String msg;
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery(sql, null);
if(cursor.moveToFirst())
	{ msg=cursor.getString(2);
	
}

else{msg="";}

return msg;
}
public String forgotPassword1(String user)
{
	
	String sql="SELECT * FROM signup where userid='"+user+"'";
	String msg;
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery(sql, null);
if(cursor.moveToFirst())
	{ msg=cursor.getString(5);
	
}

else{msg="";}

return msg;
}




//1
public Cursor getAllBooth() {
	Log.d("database",31+"AllBooth");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _booth",null);
	   Log.d("database",32+"AllBooth");
	   return cursor;
	   }
//2
public Cursor getBoothCitySector(String city,String sector,double max,double min) {
	Log.d("database",31+"getBoothCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _booth where _city='"+city+"' and _sector='"+sector+"' and _price " +
	   		"between "+min+" and "+max,null);
	   Log.d("database",32+"getBoothCitySectorRange");
	   return cursor;
	   }
//3
public Cursor getBoothCityAll(double max,double min) {
	Log.d("database",31+"getBoothCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _booth where _price between "+min+" and "+max,null);
	   Log.d("database",32+"getBoothCitySectorRange");
	   return cursor;
	   }
//4
public Cursor getBoothSectorAll(String city,double max,double min) {
	Log.d("database",31+"getBoothSetcorAll");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Log.d("","select * from _booth where _city= '"+city+"' and _price between "+min+" and "+max);
	   Cursor cursor = db.rawQuery("select * from _booth where _city= '"+city+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getBoothRange");
	   return cursor;
	   }
//5
public Cursor getBoothAddress(String address) {
	Log.d("database",31+"getBoothAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _booth where _address like'%"+address+"%'",null);
	   Log.d("database",32+"getBoothAddress");
	   return cursor;
	   }
public Cursor getBoothAddressFull(String address) {
	Log.d("database",31+"getBoothAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _booth where _address ='"+address+"'",null);
	   Log.d("database",32+"getBoothAddress");
	   return cursor;
	   }

public boolean Boothdelete(String address){
  SQLiteDatabase db=this.getWritableDatabase();
  Log.d("database",31+"delete from _booth where _address ='"+address+"'");
  db.delete(TABLE_BOOTH, KEY_ADDRESS+"='"+address+"'", null);
  //db.rawQuery("delete from _booth where_address ='"+address+"'", null);
   Log.d("database",32+"deleteBooth");
   return true;
}

//1
public Cursor getKothiCitySector(String city,String sector,double max,double min) {
	Log.d("database",31+"getkothiCitySector");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi where _city='"+city+"' and _sector='"+sector+"' and _price " +
	   		"between "+min+" and "+max,null);
	   Log.d("database",32+"getkothiCitySector");
	   return cursor;
	   }
//2
public Cursor getKothiSectorAll(String city,double max,double min) {
	Log.d("database",31+"getKothiSectorAll");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi where _city= '"+city+"' and _price between "+min+" and "+max,null);
	   Log.d("database",32+"getKothiSectorAll");
	   return cursor;
	   }
//3
public Cursor getKothiCityAll(double max,double min) {
	Log.d("database",31+"getBoothCitySectorRange");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi where _price between "+min+" and "+max,null);
	   Log.d("database",32+"getBoothCitySectorRange");
	   return cursor;
	   }
//4
public Cursor getKothiAddress(String address) {
	Log.d("database",31+"getKothiAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi where _address like'%"+address+"%'",null);
	   Log.d("database",32+"getkothiAddress");
	   return cursor;
	   }
//5
public Cursor getAllKothi() {
	Log.d("database",31+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi",null);
	   Log.d("database",32+"");
	   return cursor;
	   }

public boolean Kothidelete(String address){
	  SQLiteDatabase db=this.getWritableDatabase();
	  db.delete(TABLE_KOTHI, KEY_ADDRESS+"='"+address+"'", null);
	  //db.rawQuery("delete from _booth where_address ='"+address+"'", null);
	   Log.d("database",32+"deletesco");
	   return true;
	}

public Cursor getKothiAddressFull(String address) {
	Log.d("database",31+"getKothiAddress");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _kothi where _address ='"+address+"'",null);
	   Log.d("database",32+"getkothiAddress");
	   return cursor;
	   }

public void resetTables(){
	Log.d("database",16+"");
    SQLiteDatabase db = this.getWritableDatabase();
    // Delete All Rows
    db.delete(KEY_DATABASE, null, null);
    ;
    Log.d("database",16+"");
}

// Getting single contact


public Cursor getChandigarh() {Log.d("database",33+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _chandigarh",null);
	    ;Log.d("database",34+"");
	  // ;
	 
	   return cursor;}


public Cursor getMohali() {
	Log.d("database",35+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Log.d("database",35.1+"");
	   Cursor cursor = db.rawQuery("select * from _mohali",null);
	   Log.d("database",35.2+""); 
	   ;Log.d("database",36+"");
	  // ;
	      
	   return cursor;}

public Cursor getPanchkula() {Log.d("database",37+"");
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("select * from _panchkula",null);
	   ;Log.d("database",38+"");
	   return cursor;}

public List<String> type(){
	Log.d("database",39+"");
	List<String> typeLabel = new ArrayList<String>();
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery("select * from _type", null);
	if (cursor.moveToFirst()) {
        do {
            typeLabel.add(cursor.getString(1));
        } while (cursor.moveToNext());
    }Log.d("database",40+"");
	;
    // closing connection
    ;
   
    // returning lables
    return typeLabel;
}

public List<String> sector(){Log.d("database",41+"");
	List<String> sectorLabel = new ArrayList<String>();
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cursor=db.rawQuery("select * from _area", null);
	if (cursor.moveToFirst()) {
        do {
            sectorLabel.add(cursor.getString(1));
        } while (cursor.moveToNext());
    }
     
    // closing connection
	;
    ;
    Log.d("database",42+"");
    // returning lables
    return sectorLabel;
}

public void updateBooth(String sellername,String address,String city, String sector,int phone, double price,String condition,String adres)
{
Log.d("database",14+"");
 SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(KEY_NAME, sellername); // Name
values.put(KEY_CITY, city); // Area
values.put(KEY_ADDRESS, address); // Address
values.put(KEY_NUMBER, phone); // Number
values.put(KEY_PRICE, price); // Price
values.put(KEY_CONDITION, condition); // type
values.put(KEY_SECTOR, sector);
db.update(TABLE_BOOTH, values, KEY_ADDRESS+"='"+adres+"'",null);
}

public void UpdateSco(String sellername,String address,String city, String sector,int number, double price,String condition,
		String storey,String basement,String forsale,String adres)
    {Log.d("database",12+"");
    Log.d("name="+sellername+" add="+address+" city="+city+" sector="+sector, "number="+number+" price="+price+" con="+condition+" store="+storey
   		 +" base="+basement+" fsa="+forsale+" adres="+adres);

	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_BASEMENT, basement); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FORSALE,forsale); // type
     values.put(KEY_STOREY ,storey); // type
     values.put(KEY_SECTOR ,sector); // type
     db.update(TABLE_SCO, values, KEY_ADDRESS+"='"+adres+"'",null);
        // Closing database connection
     ;
     Log.d("database",13+"");
     
    }	

public void UpdateKothi(String sellername,String address,String city, String sector,int number, double price,String condition,
		String storey,String basement,String forsale,String adres)
    {Log.d("database",10+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 Log.d("name="+sellername+" add="+address+" city="+city+" sector="+sector, "number="+number+" price="+price+" con="+condition+" store="+storey
    		 +" base="+basement+" fsa="+forsale+" adres="+adres);

	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_BASEMENT, basement); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FORSALE,forsale); // type
     values.put(KEY_STOREY ,storey); // type
     values.put(KEY_SECTOR ,sector); // type
          db.update(TABLE_KOTHI, values, KEY_ADDRESS+"='"+adres+"'",null);
      // Closing database connection
     
     Log.d("database",11+"");
     
}

public void UpdateSociety(String sellername,String address,String society,String city,String sector,int number, double price,String condition,
		String floor,String category,String adres)
    {Log.d("database",8+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_CATEGORY, category); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FLOOR,floor); // type
     values.put(KEY_SECTOR ,sector); // type
     values.put(KEY_SOCIETY,society); // type
     db.update(TABLE_SOCIETY, values, KEY_ADDRESS+"='"+adres+"'",null);
         // Closing database connection
     ;
     Log.d("database",9+"");
   
}	

public void UpdateHb(String sellername,String address,String city,String sector,int number, double price,String condition,
		String floor,String category,String adres)
    {Log.d("database",6+"");
	 SQLiteDatabase db = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
     values.put(KEY_NAME, sellername); // Name
     values.put(KEY_CITY, city); // Area
     values.put(KEY_ADDRESS, address); // Address
     values.put(KEY_NUMBER, number); // Number
     values.put(KEY_PRICE, price); // Price
     values.put(KEY_CATEGORY, category); // type
     values.put(KEY_CONDITION, condition); // type
     values.put(KEY_FLOOR,floor); // type
     values.put(KEY_SECTOR ,sector); // type
     db.update(TABLE_HB, values, KEY_ADDRESS+"='"+adres+"'",null);
      ;
    // Closing database connection
      Log.d("database",7+"");
  
	
}	




}