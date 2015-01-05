package com.esiea.dev_mobile;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RSSPullService extends IntentService{

	public RSSPullService(String matteCaBro) {
		super(matteCaBro);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent workIntent) {
		// Get data from the incoming Intent
		Context context = getApplicationContext();
		CharSequence text = "Hello toast!";
		int duration = Toast.LENGTH_SHORT;

		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		//...
		//Do work here, based on the contents of dataString
		//...
				
		
	}
	
	
}
