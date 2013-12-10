package com.android.cloudue;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListEventTomorrow extends ListFragment{
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	ArrayList <String> list_items;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_tomorrow, container, false);
		list_items = new ArrayList<String>();
		list_items.add("Update LinkedIn profile");
		list_items.add("EEE 391 Assignment 3");
		list_items.add("IE 400 Quiz 4");
		list_items.add("CS 476 Homework 3");
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items));
		
        rootView.findViewById(R.id.tomorrow_button)
		        .setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                Intent intent = new Intent(getActivity(), AddEventActivity.class);
		                String cameFrom = "1";
		                intent.putExtra(EXTRA_MESSAGE, cameFrom);
		                startActivity(intent);
		            }
		        });	
		
		return rootView;
	}

}