package com.buyeye;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;

public class Browse extends Activity {

	private ArrayAdapter<Task> adapter;
	ParseUser currentUser;
	BuyEyeDAL tdd1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		Parse.initialize(this, this.getString(R.string.parseApplication),
				this.getString(R.string.clientKey));
		tdd1 = new BuyEyeDAL(getApplicationContext(), ParseUser.getCurrentUser());
		ListView listTasks = (ListView) findViewById(R.id.browse_tasks_list);
		final List<Task> tasks = new ArrayList<Task>();
		adapter = new TaskAdapter(this, tasks);
		adapter.add(new Task());
		listTasks.setAdapter(adapter);
		listTasks.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Task selected = tasks.get(position);
				Intent intent = new Intent(getApplicationContext(),
						ViewTaskActivity.class);
				intent.putExtra("title", selected.getTitle());
				intent.putExtra("tags", selected.getTags());
				intent.putExtra("description", selected.getDescription());
				intent.putExtra("minVote", selected.getMinVote());
				intent.putExtra("taskId", selected.getId());
				intent.putExtra("ownerID", selected.getOwnerID());
				startActivity(intent);
			}
		});
		// query:
		ParseQuery<ParseObject> query = ParseQuery.getQuery("task");
		//get user's details from cloud
		
		// TODO: add additional conditions
		// query.whereEqualTo("playerName", "Dan Stemkoski");
		List<Task> tasks1 = tdd1.findQuery(query);
		for (Task task : tasks1) {
			// title, compensation, number of possible viewers, number of
			// answers, open/closed, maybe- number of views
			adapter.add(task);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}

}
