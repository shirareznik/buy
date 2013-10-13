package com.buyeye;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseUser;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResponseAdapter extends ArrayAdapter<Response> {
	private ArrayList<Response> response_list;
	Button like;
	BuyEyeDAL tddRA=new BuyEyeDAL(getContext(), ParseUser.getCurrentUser());
	
	public ResponseAdapter(ViewTaskActivity activity, ArrayList<Response> responses) {
		
		super(activity, android.R.layout.simple_list_item_1, responses);
		response_list=responses;
	}
//red roof seatle 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Response response = getItem(position);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row_response, null);
		TextView tasktxt = (TextView)view.findViewById(R.id.response_text);
		TextView responseVotes = (TextView)view.findViewById(R.id.response_votes);
		like=(Button)view.findViewById(R.id.response_like);
		
		  like.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	
	            	 final int position = ((ListView)v.getParent().getParent()).getPositionForView((LinearLayout)v.getParent());
	                 if (position >= 0) {
	                	 if (!(response_list.get(position)).isClicked()){
	                	 tddRA.incrementLikesOfResponse(response_list.get(position));
	                	 
	 	            	//Toast.makeText(getContext(), "Liked!",Toast.LENGTH_SHORT).show();
	 	            	((Button)v).setAlpha((float)0.3);
	 	            	
	 	            	(response_list.get(position)).setClicked(true);
	 	            	//like.setAlpha((float) 0.3);
	                	 }
	                 }
	            	
	            }
	        });
	        
		//TextView 
		//this is a header
		if (position==0)
		{
			tasktxt.setText("Answers");
			tasktxt.setTextColor(getContext().getResources().getColor(R.color.text_color));
			tasktxt.setTypeface(null, Typeface.BOLD);
			tasktxt.setBackgroundColor(getContext().getResources().getColor(R.color.text_bg));
			like.setAlpha(0);
			//tasktxt.setWidth(50);
		}
		//this is a task
		else
		{
			tasktxt.setText(response.getResponse());
			responseVotes.setText(Integer.toString(response.getLikes())+" likes");
			tasktxt.setTextColor(getContext().getResources().getColor(R.color.text_color));
			responseVotes.setTextColor(getContext().getResources().getColor(R.color.text_color));
		/*
		tasktxt.setText(res.getTitle());
		tasktxt.setTypeface(null, Typeface.BOLD);
		compensation.setText(Integer.toString(task.getCompensation())+"$");
		*/
		}
		return view;
	}
}
