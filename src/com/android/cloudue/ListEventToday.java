package com.android.cloudue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListEventToday extends ListFragment{
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	public Context context;
	ArrayList<String> list_items;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list_items = new ArrayList<String>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("DueEvent");
		query.whereEqualTo("listIndex", 0);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				if(e == null) {
					for(ParseObject object : objects) {
						list_items.add(object.getString("detail"));
					}
				}
			}
		});
		
		System.out.println("data is fetched");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_today, container, false);
		
		System.out.println("on create view cagirildi");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items);
		setListAdapter(adapter);
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
}