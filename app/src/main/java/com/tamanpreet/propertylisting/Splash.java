package com.tamanpreet.propertylisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Splash extends Activity 
{
	int splashtime=2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.splash);
		

		Handler x= new Handler();
		x.postDelayed(new SplashActivity(), splashtime);
		
		
	}
	class SplashActivity implements Runnable
	{
		@Override
		public void run()
		{
			
			 startActivity(new Intent(getApplicationContext(),login.class));
			 Splash.this.finish();
			 Toast.makeText(getApplicationContext(), "Welcome ", Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
