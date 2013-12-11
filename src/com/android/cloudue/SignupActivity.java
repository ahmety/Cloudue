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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SignupActivity extends Activity {
	String userName;
	String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}

	public void signup(View view){
		//TODO method stub
		EditText unEdit = (EditText)findViewById(R.id.username_signup);
		EditText passwordEditText = (EditText)findViewById(R.id.password_signup);
		userName = unEdit.getText().toString();
		password = passwordEditText.getText().toString();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Users");
		query.whereEqualTo("username", userName);
		
		//TODO whitespace checking
		if(userName.equals("User")){
			//TODO dialog
			Toast toast = Toast.makeText(getApplicationContext(), "Username is not available!", Toast.LENGTH_SHORT);
			toast.show();
			System.out.println("Username is not available!");
			return;
		}
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				// TODO Auto-generated method stub
				if(object == null) {
					ParseObject user = new ParseObject("Users");
					user.put("username", userName);
					user.put("password", password);
					user.saveInBackground();
					ParseObject userDueList = new ParseObject(userName+"DueList");
					userDueList.saveInBackground();
				}
				else{
					//TODO dialog
					Toast toast = Toast.makeText(getApplicationContext(), "Username is already in use!", Toast.LENGTH_SHORT);
					toast.show();

					System.out.println("Username is already in use!");
					return;
				}
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				SharedPreferences.Editor editor = preferences.edit();
				editor.clear();
				editor.putString("userName", userName);
				editor.commit();
				Intent intent = new Intent (getApplicationContext(), MainActivity.class);
				intent.putExtra("userName", userName);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}
	
}
