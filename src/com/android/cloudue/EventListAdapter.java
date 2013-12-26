package com.android.cloudue;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<EventData> {
	Context context;
	ArrayList<EventData> list_items;
	
	public EventListAdapter(Context context, int textViewResourceId,
			ArrayList<EventData> list_items) {
		super(context, textViewResourceId, list_items);
		this.context = context;
		this.list_items = list_items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = 
				(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.event_row, parent, false);
		TextView eventContent = (TextView)rowView.findViewById(R.id.row_event);
		eventContent.setText(list_items.get(position).getDetail());
		
		if(list_items.get(position).getSharingUser() != null) {
			TextView eventSharingUserContent = (TextView)rowView.findViewById(R.id.row_event_shared);
			eventSharingUserContent.setText(list_items.get(position).getSharingUser());
		}
		
		
		return rowView;
	}

}