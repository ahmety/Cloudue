package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;

public class AddEventActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";
	String message;
	
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
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_event, menu);
		return true;
	}
	public void addCalendarEvent(View view){
		
		EditText editText = (EditText) findViewById(R.id.event_name);
		String eventName = editText.getText().toString();
		if(eventName.equals("")){
			return;
		}
		
		int listIndex = Integer.parseInt(message);
		
		ParseObject dueEvent = new ParseObject("DueEvent");
		dueEvent.put("detail", eventName);
		dueEvent.put("listIndex", listIndex);
		dueEvent.saveInBackground();
		
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		
//		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		SharedPreferences.Editor editor = prefs.edit();
//		editor.putString("string_id", message); //InputString: from the EditText
//		editor.commit();
		
		
		
		//if event came from today list layout
		
		//else if event came from tomorrow list layout
		//else (came from someday list layout)
		
//		ParseObject kimiObject = new ParseObject("KimiObject");
//		kimiObject.put("foo", "bar");
//		kimiObject.saveInBackground();
	}
}
