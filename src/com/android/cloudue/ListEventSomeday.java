package com.android.cloudue;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListEventSomeday extends ListFragment implements FragmentCommunicator{
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	public Context context;
	private ActivityCommunicator activityCommunicator;
	ArrayList<String> list_items;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		context = getActivity();
		activityCommunicator = (ActivityCommunicator)context;
		((MainActivity)context).fragmentCommunicator = this;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_someday, container, false);
		list_items = new ArrayList<String>();		
		list_items.add("Sleep");
		list_items.add("Eat");
		list_items.add("Work");
		
		System.out.println("on create cagirildi");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items);
		setListAdapter(adapter);
		rootView.findViewById(R.id.someday_button)
        		.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                Intent intent = new Intent(getActivity(), AddEventActivity.class);
		                String cameFrom = "2";
		                intent.putExtra(EXTRA_MESSAGE, cameFrom);
		                startActivity(intent);
		            }
		        });	
		return rootView;
	}

	@Override
	public void sendDataToFragment(String value) {
		// TODO Auto-generated method stub
		
	}

}