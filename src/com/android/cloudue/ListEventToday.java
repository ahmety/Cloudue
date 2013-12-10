package com.android.cloudue;

import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListEventToday extends ListFragment{
	
	public final static String EXTRA_MESSAGE = "com.android.cloudue.MESSAGE";	
	ArrayList <String> list_items;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.list_today, container, false);
		list_items = new ArrayList<String>();
		//TODO: Each time app started, the contents of the list will be crawled from the cloud
		list_items.add("CS 443 Midterm 2");
		list_items.add("Attend to the project meeting");
		list_items.add("Go to the Gym");
		list_items.add("CS 491 Report");
		list_items.add("Pay the Credit Card debt");
		list_items.add("Return the books to Library");
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_items));
		
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