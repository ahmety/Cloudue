package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventDetailsActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";
	String eventName;
	String listNo;
	String sharedUser;
	String userName;
	boolean shareButtonPressed = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		Intent intent = getIntent();
		String[] itemInfo = intent.getStringArrayExtra("itemInfo");
	    eventName = itemInfo[1];
	    listNo = itemInfo[0];
	    
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    userName = preferences.getString("userName", "");
	    
		TextView textView = (TextView)findViewById(R.id.event_detail);
		textView.setText(eventName);
		
	}
	
	public void removeEvent(View view){
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(userName + "DueList");
		query.whereEqualTo("listIndex", Integer.parseInt(listNo));
		query.whereEqualTo("detail", eventName);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				if(object != null) {
					object.deleteInBackground();
					}
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("currentTab", listNo);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}
	public void shareEvent(View view){
		EditText etSharedUser = (EditText)findViewById(R.id.shared_username);
		sharedUser = etSharedUser.getText().toString();
		if(!sharedUser.equalsIgnoreCase("") && !shareButtonPressed){	
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Users");
			query.whereEqualTo("username", sharedUser);
			query.getFirstInBackground(new GetCallback<ParseObject>() {
				@Override
				public void done(ParseObject object, ParseException e) {
					if(object == null) {
						Toast toast = Toast.makeText(getApplicationContext(), "Unknown user!", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					
					else{
						ParseObject dueEvent = new ParseObject(sharedUser + "DueList");
						dueEvent.put("detail", eventName);
						dueEvent.put("listIndex", Integer.parseInt(listNo));
						dueEvent.put("shared", "from " + userName);
						dueEvent.saveInBackground();
						shareButtonPressed = true;
						Toast toast = Toast.makeText(getApplicationContext(), "Event has been shared with "+sharedUser, Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
				}
			});
		}
	}
}
