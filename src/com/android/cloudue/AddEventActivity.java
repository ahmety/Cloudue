package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.parse.ParseObject;

public class AddEventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message;
	    String temp;

	    if ((temp = intent.getStringExtra(ListEventToday.EXTRA_MESSAGE)) != null) {
	    	message = temp;
	    }
	    else if ((temp = intent.getStringExtra(ListEventTomorrow.EXTRA_MESSAGE)) != null) {
	    	message = temp;
	    }
	    else {
	    	message = intent.getStringExtra(ListEventSomeday.EXTRA_MESSAGE);
	    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_event, menu);
		return true;
	}
	public void addCalendarEvent(View view){
		//if event came from today list layout
		
		//else if event came from tomorrow list layout
		//else (came from someday list layout)
		
		
//		ParseObject kimiObject = new ParseObject("KimiObject");
//		kimiObject.put("foo", "bar");
//		kimiObject.saveInBackground();
	}
}
