package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventDetailsActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";
	String eventName;
	String listNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		Intent intent = getIntent();
		String[] itemInfo = intent.getStringArrayExtra("itemInfo");
	    eventName = itemInfo[1];
	    listNo = itemInfo[0];
	    
	    
		TextView textView = (TextView)findViewById(R.id.event_detail);
		textView.setText(eventName);
		
	}
	
	public void removeEvent(View view){
		System.out.println("inside removeevent");
		System.out.println("eventName: " + eventName);
	    System.out.println("List no: " + listNo);
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String userNameDueList = preferences.getString("userName", "");
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(userNameDueList + "DueList");
		System.out.println(userNameDueList);
		query.whereEqualTo("listIndex", Integer.parseInt(listNo));
		query.whereEqualTo("detail", eventName);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				if(object != null) {
					object.deleteInBackground();
					System.out.println(object.getString("detail"));
					System.out.println(object.getNumber("listIndex"));
				}
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("currentTab", listNo);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}

}
