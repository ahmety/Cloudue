package com.android.cloudue;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

public class MainActivity extends FragmentActivity {
	
	private ViewPager pager;
	private TabAdapter mTabAdapter;
	private String userName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pager = new ViewPager(this);
		pager.setId(R.id.pager);
		setContentView(pager);
		
		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Parse.initialize(this, "StrOqB8qnqpisT8hL6gn3ru4R30Iz4odmzQasElG", "5VdNwSQNvvzNex7dvq4R0RexZD3J5r3HaqpvHOGi");
		
		mTabAdapter = new TabAdapter(this, pager);
		mTabAdapter.addTab(bar.newTab().setText("Today"), ListEventToday.class, null);
		mTabAdapter.addTab(bar.newTab().setText("Tomorrow"), ListEventTomorrow.class, null);
		mTabAdapter.addTab(bar.newTab().setText("Someday"), ListEventSomeday.class, null);
		
		Intent intent = getIntent();
		if(intent != null){
			String currentTab = intent.getStringExtra("currentTab");
			if(currentTab != null)
				mTabAdapter.onPageSelected(Integer.parseInt(currentTab));
		}
		
		if(intent != null) {
			userName = intent.getStringExtra("userName");
		}
		
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if( item.getItemId() == R.id.logout_menu_button){
    		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    		SharedPreferences.Editor editor = preferences.edit();
			editor.clear();
			editor.commit();
			
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();

    	    return true;
    	}
    	
    	else{
        	return super.onOptionsItemSelected(item);
        }
    }

}

