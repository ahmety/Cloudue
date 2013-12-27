package com.android.cloudue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

public class AddEventActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";
	String cameFrom;
	String userNameDueList;
	String[] list_items;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		userNameDueList = preferences.getString("userName", "");
		if(!userNameDueList.equalsIgnoreCase("")) {
			userNameDueList += "DueList";
		}
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String temp;

	    if ((temp = intent.getStringExtra(ListEventToday.EXTRA_MESSAGE)) != null) {
	    	cameFrom = temp;
	    }
	    else if ((temp = intent.getStringExtra(ListEventTomorrow.EXTRA_MESSAGE)) != null) {
	    	cameFrom = temp;
	    }
	    else {
	    	cameFrom = intent.getStringExtra(ListEventSomeday.EXTRA_MESSAGE);
	    }
	    list_items = intent.getStringArrayExtra("list_items");
	    
	}
	
	public void addCalendarEvent(View view){
		
		EditText editText = (EditText) findViewById(R.id.event_name);
		String eventName = editText.getText().toString();
		if(eventName.equals("")){
			return;
		}
		
		for(int i = 0; i < list_items.length; i++) {
			if(eventName.equals(list_items[i])){
				Toast toast = Toast.makeText(getApplicationContext(), "Event already exists!", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
		}
		
		
		int listIndex = Integer.parseInt(cameFrom);
		
		ParseObject dueEvent = new ParseObject(userNameDueList);
		dueEvent.put("detail", eventName);
		dueEvent.put("listIndex", listIndex);
		dueEvent.saveInBackground();
		
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra("currentTab", cameFrom);
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
