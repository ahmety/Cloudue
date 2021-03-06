package com.android.cloudue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListEventSomeday extends ListFragment {
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	public Context context;
	ArrayList<EventData> list_items;
	String userNameDueList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list_items = new ArrayList<EventData>();
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		userNameDueList = preferences.getString("userName", "");
		if(!userNameDueList.equalsIgnoreCase("")) {
			userNameDueList += "DueList";
		}
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery(userNameDueList);
		query.whereEqualTo("listIndex", 2);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				if(e == null) {
					for(ParseObject object : objects) {
						String detail = object.getString("detail");
						String sharingUser = object.getString("shared");
//						Log.v(null, detail);
//						Log.v(null, sharingUser);
						list_items.add(new EventData(detail, sharingUser));
					}
				}
				//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items);
				EventListAdapter adapter = new EventListAdapter(getActivity(), R.layout.event_row, list_items);
				setListAdapter(adapter);
			}
		});
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	     setListAdapter(new EventListAdapter(getActivity(), R.layout.event_row,list_items));
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_someday, container, false);
		rootView.findViewById(R.id.someday_button)
        		.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                Intent intent = new Intent(getActivity(), AddEventActivity.class);
		                String cameFrom = "2";
		                intent.putExtra(EXTRA_MESSAGE, cameFrom);
		                int size = list_items.size();
		                String[] dueList = new String[size];
		                for(int i = 0; i < size; i++) {
		                	dueList[i] = list_items.get(i).getDetail();
		                }
		                intent.putExtra("list_items", dueList);
		                startActivity(intent);
		            }
		        });
		
		rootView.findViewById(R.id.refresh_button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				ParseQuery<ParseObject> query = ParseQuery.getQuery(userNameDueList);
				query.whereEqualTo("listIndex", 2);
				query.findInBackground(new FindCallback<ParseObject>() {
				
					@Override
					public void done(List<ParseObject> objects,
							com.parse.ParseException e) {
						if(e == null) {
							list_items.clear();
							for(ParseObject object : objects) {
								list_items.add(new EventData(object.getString("detail"), object.getString("shared")));
							}
						}
						EventListAdapter adapter = new EventListAdapter(getActivity(), R.layout.event_row, list_items);
						setListAdapter(adapter);
					}
				});
				
			}
		});
		return rootView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String eventName = list_items.get(position).getDetail();
		String[] itemInfo = {"2",eventName};
		Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
		intent.putExtra("itemInfo", itemInfo);
		startActivity(intent);
	}
	
}