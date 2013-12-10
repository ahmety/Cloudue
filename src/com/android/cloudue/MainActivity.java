package com.android.cloudue;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.parse.Parse;

public class MainActivity extends FragmentActivity{
	
	private ViewPager pager;
	private TabAdapter mTabAdapter;
	
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
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

