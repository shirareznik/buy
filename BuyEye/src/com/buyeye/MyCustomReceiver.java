package com.buyeye;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyCustomReceiver extends BroadcastReceiver {

	/*
	 * @Override public void onReceive(Context context, Intent intent) { try {
	 * String action = intent.getAction(); String channel =
	 * intent.getExtras().getString("com.parse.Channel"); JSONObject json = new
	 * JSONObject(intent.getExtras().getString("com.parse.Data"));
	 * 
	 * Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
	 * Iterator itr = json.keys(); while (itr.hasNext()) { String key = (String)
	 * itr.next(); Log.d(TAG, "..." + key + " => " + json.getString(key)); } }
	 * catch (JSONException e) { Log.d(TAG, "JSONException: " + e.getMessage());
	 * } }
	 */

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();  
		JSONObject json= new JSONObject();
		try {
			 json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	    Intent viewer = new Intent(context, ViewTaskActivity.class);
	    try {
			viewer.putExtra("title",json.getString("title"));
			 viewer.putExtra("tags",json.getString("tags"));
			    viewer.putExtra("description", json.getString("desc"));
			    viewer.putExtra("minVote", json.getInt("minVote"));
			    viewer.putExtra("taskId", json.getString("taskId"));
			    viewer.putExtra("ownerID", json.getString("ownerID"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    viewer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   context.startActivity(viewer);
	    
		
	}
}
