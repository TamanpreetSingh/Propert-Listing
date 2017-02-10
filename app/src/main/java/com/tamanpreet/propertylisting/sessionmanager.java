package com.tamanpreet.propertylisting;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class sessionmanager
{
  SharedPreferences pref;
  Editor editor;
  Context context1;
  public sessionmanager(Context context)
  {
	  this.context1=context;
	  pref=context1.getSharedPreferences("oopsinfo", 0);
	  editor=pref.edit();
	  
  }
   public void createloginsession(String name, String password )
   {
	   try
	   {
	   editor.putString("user", name);
	   editor.putString("pass", password);
	   editor.commit();
	   }
	   catch(Exception e)
	   {
		   
	   }
	   
   }
   public HashMap<String , String> getuserdetail()
   {
	   HashMap<String , String> user =new HashMap<String, String>();
	   user.put("username", pref.getString("user", "no user name in session"));
	   user.put("password", pref.getString("pass", "no pass in session "));
	   
	return user;
   }
   public void  logoutuser()
   {
	editor.clear();
	editor.commit();

   
   }
}
