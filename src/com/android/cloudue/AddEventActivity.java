package com.android.cloudue;

import com.parse.ParseObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddEventActivity extends Activity {
	String message;
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";
	Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		
	    // Get the message from the intent
	    Intent intent = getIntent();
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
	    
	    bundle = savedInstanceState;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_event, menu);
		return true;
	}
	public void addCalendarEvent(View view){
		String changes;
		if(message.equals("0")){
			EditText editText = (EditText) findViewById(R.id.event_name);
			changes = editText.getText().toString();
			//add changes to ListEventToday's array list in cloud		
		}

		else if(message.equals("1")){
			EditText editText = (EditText) findViewById(R.id.event_name);
			changes = editText.getText().toString();
			//add changes to ListEventTomorrow's array list in cloud
		}
		else if(message.equals("2")){
			EditText editText = (EditText) findViewById(R.id.event_name);
			changes = editText.getText().toString();
			//add changes to ListEventSomeday's array list in cloud
		}
		else{
			//do nothing
		}
		
		
//		ParseObject kimiObject = new ParseObject("KimiObject");
//		kimiObject.put("foo", "bar");
//		kimiObject.saveInBackground();
	}
}
