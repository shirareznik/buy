package com.buyeye;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<Task> {

	//ArrayList tasks, dates;
	private ArrayList<Task> tasks;

	public TaskAdapter(
			Browse activity, List<Task> tasks) {
		super(activity, android.R.layout.simple_list_item_1, tasks);
	}
//red roof seatle 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Task task = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row, null);
		TextView tasktxt = (TextView)view.findViewById(R.id.title);
		TextView compensation=(TextView)view.findViewById(R.id.compensation);
		//TextView 
		//this is a header
		tasktxt.setTextColor(getContext().getResources().getColor(R.color.text_color));
		compensation.setTextColor(getContext().getResources().getColor(R.color.text_color));
		if (position==0)
		{
			tasktxt.setText("Title");
			tasktxt.setTypeface(null, Typeface.BOLD);
		
			tasktxt.setBackgroundColor(getContext().getResources().getColor(R.color.text_bg));
			compensation.setText("Compensation ($)");
			compensation.setTypeface(null, Typeface.BOLD);	
			compensation.setTextColor(getContext().getResources().getColor(R.color.text_color));
			compensation.setBackgroundColor(getContext().getResources().getColor(R.color.text_bg));

		}
		//this is a task
		else
		{
		
		tasktxt.setText(task.getTitle());
		tasktxt.setTypeface(null, Typeface.BOLD);
		compensation.setText(Integer.toString(task.getCompensation())+"$");
		}
		return view;
	}
}
