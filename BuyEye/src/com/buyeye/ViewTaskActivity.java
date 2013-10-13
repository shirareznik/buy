package com.buyeye;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ViewTaskActivity extends Activity {

	private ArrayAdapter<Response> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final Bundle extras = getIntent().getExtras(); 
		 final Intent i= getIntent();
		setContentView(R.layout.activity_view_task);
		TextView title = (TextView)findViewById(R.id.view_title);
		title.setText(i.getStringExtra("title"));
		TextView description = (TextView)findViewById(R.id.view_description);
		description.setText(i.getStringExtra("description"));
		TextView compensation = (TextView)findViewById(R.id.view_compensation);
		compensation.setText("Prize is set to: "+Integer.toString(i.getIntExtra("compensation",0)));
		compensation.setTextColor(this.getResources().getColor(R.color.text_color));
		TextView minVotes = (TextView)findViewById(R.id.view_min_votes);
		minVotes.setText("The minimal required vote for winning is: "+Integer.toString(i.getIntExtra("minVote",0)));
		minVotes.setTextColor(this.getResources().getColor(R.color.text_color));
		Button respondButton=(Button)findViewById(R.id.view_respond);
		
		final EditText edit_response=(EditText)findViewById(R.id.view_response);
		respondButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//here- depends on whether automatic or after 
				String response=edit_response.getText().toString();
				
				ParseObject parseObject = new ParseObject("response");
				parseObject.put("ownerID", i.getStringExtra("ownerID"));
				parseObject.put("response", response);
				parseObject.put("taskId", i.getStringExtra("taskId"));
				parseObject.put("responderID", ParseUser.getCurrentUser());
				parseObject.put("likes", 0);
				//to remove this try and catch
				try {
					parseObject.save();
				} catch (com.parse.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
				//add a push notification to owner
				//add balance save
				//add on result:  Toast toast = Toast.makeText(getApplicationContext(), "Your response was saved",Toast.LENGTH_SHORT);
				 //toast.show();
				
				//DialogFragment newFragment = new CompensationDialog();
				//newFragment.show(getFragmentManager(), "compensation");
			}
		});
		//set list of answers:
		 ListView listTasks =(ListView)findViewById(R.id.view_responses);
	        final ArrayList<Response> responses = new ArrayList<Response>();
	        adapter = new ResponseAdapter(this, responses);
	        adapter.add(new Response());
	        listTasks.setAdapter(adapter);
	        
	        ParseQuery<ParseObject> query = ParseQuery.getQuery("response");
			//query.whereEqualTo("playerName", "Dan Stemkoski");
	        query.whereEqualTo("taskId", i.getStringExtra("taskId"));
	        
			query.findInBackground(
					new FindCallback<ParseObject>() {
						@Override
						public void done(List<ParseObject> objects,
								com.parse.ParseException e) {
							   if (e == null) {
								   for (ParseObject object : objects) {
									   // title, compensation, number of possible viewers, number of answers, open/closed, maybe- number of views
									     Response t=new Response();
									     t.setId(object.getObjectId());
									     t.setResponderID(object.getString("responderID"));
									     t.setResponse(object.getString("response"));
									     t.setTaskId(object.getString("taskId"));
									     t.setLikes(object.getInt("likes"));
									     adapter.add(t);
								   }
						        } else {
						            //Log.d("score", "Error: " + e.getMessage());
						        }
							
						}	    
			});
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_task, menu);
		return true;
	}

}
