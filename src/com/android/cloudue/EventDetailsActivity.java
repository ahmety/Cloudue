package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class EventDetailsActivity extends Activity {

	String eventName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		Intent intent = getIntent();
	    eventName = intent.getStringExtra(ListEventSomeday.EXTRA_MESSAGE);
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
		
	}

}
