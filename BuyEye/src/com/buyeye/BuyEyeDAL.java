package com.buyeye;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class BuyEyeDAL {

	Context context;
	private SQLiteDatabase db;
	private int usersNum = -1;
	private List<ParseObject> results;
	
	
	public BuyEyeDAL(Context context, ParseUser user) {
		this.context = context;
	}

	public boolean insertTask(Task task) {
		try {
			ParseObject parseObject = new ParseObject("task");
			parseObject.put("ownerID", task.ownerID);
			parseObject.put("title", task.title);
			parseObject.put("description", task.description);
			parseObject.put("tags", task.getTags());
			parseObject.put("minAge", task.minAge);
			parseObject.put("maxAge", task.maxAge);
			parseObject.put("compensation", task.getCompensation());
			parseObject.put("minVote", task.getMinVote());
			// to remove this try and catch
			try {
				parseObject.save();
				task.setId(parseObject.getObjectId());
			} catch (com.parse.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Send push notification to query
			ParsePush push = new ParsePush();
			push.setQuery(task.getFilter()); // Set our Installation query
			push.setMessage("You were found to be qualified for a new task!");
			JSONObject data = new JSONObject();
			try {
				data.put("action", "com.buyeye.NEW_TASK");
				data.put("description", task.getTitle());
				data.put("taskId", task.getId());
				data.put("title", task.getTitle());
				data.put("desc", task.getTitle());
				data.put("tags", task.getTags());
				data.put("minVote", task.getMinVote());
				data.put("taskId", task.getId());
				data.put("ownerID", task.getOwnerID());
			} catch (JSONException e) {
			}
			push.setData(data);
			push.sendInBackground();
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean incrementLikesOfResponse(Response response) {  
		try {
			ParseQuery query = new ParseQuery("response");
			query.whereEqualTo("objectId", response.getId());
			List<ParseObject> objects=query.find();
			if (objects.size()<1)
				return false;
			
				for (ParseObject object : objects) {
					object.increment("likes");
					object.save();
				}

		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/*
	public boolean incrementCredForResponse(Response response) {  
		try {
			ParseQuery query = new ParseQuery("response");
			query.whereEqualTo("objectId", response.getId());
			List<ParseObject> objects=query.find();
			if (objects.size()<1)
				return false;
			
				for (ParseObject object : objects) {
					
					String responderID= object.getString("responderID");
					ParseQuery<ParseInstallation> q=ParseInstallation.getQuery();
					q.whereEqualTo("", value)
					
					// Saving the device's owner
			
					
					//object.increment("likes");
					//object.save();
				}

		} catch (Exception e) {
			return false;
		}
		return true;
	}
	*/
	
	public int getNumberOfUsers() {
		try {
			ParseQuery query = ParseUser.getQuery();
			query.countInBackground(new CountCallback() {
				public void done(int count, com.parse.ParseException e) {
					if (e == null) {
						usersNum = count;
					} else {

					}
				}
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		return usersNum;
	}

	public List<Task> findQuery(ParseQuery<ParseObject> query) {
		List<Task> results1=new ArrayList<Task>();
		try {
			List<ParseObject> objects=query.find();
			for (ParseObject object:objects){
				Task t = new Task(object.getString("ownerID"),
						object.getString("description"), object.getInt("minAge"),
						object.getInt("maxAge"), object.getInt("minVote"),
						object.getInt("compensation"), object.getString("title"),
						object.getString("tags"));
				t.setId(object.getObjectId());
				results1.add(t);
			}
			
		} catch (com.parse.ParseException e) {
			// TODO Auto-generated catch block
			
		}
		return results1;
		
		/*
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects,
					com.parse.ParseException e) {
				Task t;
				if (e == null) {
					for (ParseObject object:objects){
						t = new Task(object.getString("ownerID"),
								object.getString("description"), object.getInt("minAge"),
								object.getInt("maxAge"), object.getInt("minVote"),
								object.getInt("compensation"), object.getString("title"),
								object.getString("tags"));
						t.setId(object.getObjectId());
						results1.add(t);
					}
					return results1;
				} else {
					// Log.d("score", "Error: " + e.getMessage());
				}
			}
		});
		*/
	}
	/*
	 * Context context; private SQLiteDatabase db;
	 * 
	 * public TodoDAL(Context context) { this.context = context;
	 * TasksDatabaseHelper helper = new TasksDatabaseHelper(context); db =
	 * helper.getWritableDatabase(); Parse.initialize(context,
	 * context.getString(R.string.parseApplication),
	 * context.getString(R.string.clientKey)); ParseUser.enableAutomaticUser();
	 * }
	 * 
	 * public boolean insert(ITodoItem todoItem) { try { ContentValues taskData
	 * = new ContentValues(); ParseObject parseObject = new ParseObject("todo");
	 * long dbTime = 0; taskData.put("title", todoItem.getTitle());
	 * parseObject.put("title", todoItem.getTitle()); if (todoItem.getDueDate()
	 * != null) { dbTime = todoItem.getDueDate().getTime(); // ask regarding the
	 * // -1 issues taskData.put("due", dbTime); parseObject.put("due", dbTime);
	 * } else { taskData.putNull("due"); } db.insert("todo", null, taskData);
	 * parseObject.save(); } catch (ParseException e) { return false; } return
	 * true; }
	 * 
	 * public boolean update(ITodoItem todoItem) { try { ParseQuery query = new
	 * ParseQuery("todo"); query.whereEqualTo("title", todoItem.getTitle());
	 * List<ParseObject> objects = query.find(); if (objects.size() < 1) return
	 * false; ContentValues args = new ContentValues(); long updateDue = 0; if
	 * (todoItem.getDueDate() != null) { updateDue =
	 * todoItem.getDueDate().getTime(); args.put("due", updateDue);
	 * db.update("todo", args, "title" + "='" + todoItem.getTitle() + "'",
	 * null); for (ParseObject object : objects) { object.put("due", updateDue);
	 * object.save(); } } else { for (ParseObject object : objects) {
	 * object.remove("due"); object.save(); args.putNull("due");
	 * db.update("todo", args, "title" + "='" + todoItem.getTitle() + "'",
	 * null); } }
	 * 
	 * } catch (Exception e) { return false; } return true; }
	 * 
	 * public boolean delete(ITodoItem todoItem) {
	 * 
	 * try { db.delete("todo", "title" + "='" + todoItem.getTitle() + "'",
	 * null); ParseQuery query = new ParseQuery("todo");
	 * query.whereEqualTo("title", todoItem.getTitle()); List<ParseObject>
	 * objects = query.find();// TODO check if deleted // more than needed! if
	 * (objects.size() < 1) return false; for (ParseObject object : objects) {
	 * try { object.delete();
	 * 
	 * } catch (ParseException e) { e.printStackTrace(); throw new
	 * RuntimeException(); } } } catch (Exception e) { return false; } return
	 * true; }
	 * 
	 * public List<ITodoItem> all() {
	 * 
	 * List<ITodoItem> items = new ArrayList<ITodoItem>();
	 * 
	 * try { items = new ArrayList<ITodoItem>(); String selectQuery =
	 * "SELECT  * FROM " + "todo"; Cursor cursor = db.rawQuery(selectQuery,
	 * null);
	 * 
	 * // looping through all rows and adding to list if (cursor.moveToFirst())
	 * { do { Task t = new Task(cursor.getString(1), new Date(
	 * cursor.getLong(2))); items.add(t); } while (cursor.moveToNext()); } }
	 * catch (Exception e) { // } return items; // check if there is a problem
	 * with the "final" }
	 */

}
