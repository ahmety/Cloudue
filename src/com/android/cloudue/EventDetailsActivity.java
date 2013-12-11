package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventDetailsActivity extends Activity {

	String eventName;
	String listNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		Intent intent = getIntent();
		String[] itemInfo = intent.getStringArrayExtra(ListEventSomeday.EXTRA_MESSAGE);
	    eventName = itemInfo[1];
	    listNo = itemInfo[0];
		TextView textView = (TextView)findViewById(R.id.event_detail);
		textView.setText(eventName);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}
	
	public void removeEvent(View view){
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("DueEvent");
		query.whereEqualTo("listIndex", Integer.parseInt(listNo));
		query.whereEqualTo("detail", eventName);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				if(object != null) {
					System.out.println("icerde");
					object.deleteInBackground();
					System.out.println(object.getString("detail"));
					System.out.println(object.getNumber("listIndex"));
				}
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}

}
