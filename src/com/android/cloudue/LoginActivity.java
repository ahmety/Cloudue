package com.android.cloudue;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LoginActivity extends Activity {
	String userName;
	String password;
	
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
		EditText etUserName = (EditText)findViewById(R.id.username_login);
		EditText etPassword = (EditText)findViewById(R.id.password_login);
		userName = etUserName.getText().toString();
		password = etPassword.getText().toString();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Users");
		query.whereEqualTo("username", userName);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				if(object == null) {
					Toast toast = Toast.makeText(getApplicationContext(), "Unknown user!", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					if(object.getString("password").equals(password)) {
						SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
						SharedPreferences.Editor editor = preferences.edit();
						editor.clear();
						editor.putString("userName", userName);
						editor.commit();
						
						Intent intent = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(intent);
						finish();
					}
					else {
						Toast toast = Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_SHORT);
						toast.show();
					}
				}
			}
		});
		
	}
	public void showSignupActivity(View view){
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}
}
