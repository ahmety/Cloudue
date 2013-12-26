package com.android.cloudue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListEventToday extends ListFragment {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	public Context context;
	ArrayList<String> list_items;
	String userNameDueList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list_items = new ArrayList<String>();
		
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		userNameDueList = preferences.getString("userName", "");
		if(!userNameDueList.equalsIgnoreCase("")) {
			userNameDueList += "DueList";
		}
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery(userNameDueList);
		query.whereEqualTo("listIndex", 0);
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				String sharedEvents = "";
				String sharingUsers = "";
				if(e == null) {
					for(ParseObject object : objects) {
						if(object.getString("shared") != null) {
							sharedEvents += object.getString("detail") + "\n";
						}
						list_items.add(object.getString("detail"));
					}
					
					Toast toast = Toast.makeText(getActivity().getApplicationContext(), "shared the event with you", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items);
				setListAdapter(adapter);
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_today, container, false);
		rootView.findViewById(R.id.today_button)
        		.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                Intent intent = new Intent(getActivity(), AddEventActivity.class);
		                String cameFrom = "0";
		                intent.putExtra(EXTRA_MESSAGE, cameFrom);
		                startActivity(intent);
		            }
		        });
		
		return rootView;
	}	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String eventName = list_items.get(position);
		String[] itemInfo = {"0",eventName};
		Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
		intent.putExtra("itemInfo", itemInfo);
		startActivity(intent);
	}	
}