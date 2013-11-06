package com.era.zakat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class splash extends Activity {
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.splash);
	      
	      final splash sPlashScreen = this; 
	      
	      Thread splashThread = new Thread() {
	         @Override
	         public void run() {
	            try {
	               int waited = 0;
	               while (waited < 2000) {
	                  sleep(100);
	                  waited += 100;
	               }
	            } catch (InterruptedException e) {
	               // do nothing
	            } finally {
	               finish();
	               Intent i = new Intent();
	               i.setClass(sPlashScreen,MainActivity.class);
	               startActivity(i);
	            }
	         }
	      };
	      splashThread.start();
	   }

}
