package com.android.cloudue;

import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Parse.initialize(this, "StrOqB8qnqpisT8hL6gn3ru4R30Iz4odmzQasElG", "5VdNwSQNvvzNex7dvq4R0RexZD3J5r3HaqpvHOGi");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void login(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	public void showSignupActivity(View view){
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}
}
