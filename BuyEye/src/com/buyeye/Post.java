package com.buyeye;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.parse.*;

/*
 * cases to test: login failures
 * 
 * TODO :
 * 1. facebook integration (2 days)
 * 2. post by url? (1-2 days)
 * 3. add multiple compensation levels
 * 4. add an option to post from facebook/stack?
 * 5. finish implement push-es.	
 * 6. implement browsing interesting posts- browse   	
 * 	to show in list: title, compensation, number of possible viewers, number of answers, open/closed, maybe- number of views
 *	possibly- add compensation for, with links to posts
 * 7. create page showing post					
 * 	ownerID ranking
	description;
	//int minVote;
	compensation;
	title
	description
	tags;
 * 8. create settings page 						
 * 9. money transactions between users			1
 * 10. add adapter to show filters on screen after filter finishes
 * 11. Change profile tags to automatic completion according to a list of 
 * 		tags on cloud.							1
 * 12. Implement responding to post				
 * 13. general tabs interface					
 * 14. implement (x people will see this post)
 * 15. Automatic building of tags
 * 16. listing of post-add titles
 * 17. add show of responses in browse layout   1
 */

/*
 * saturday:  missing parts and data, gps/ more filters, paypal, integration with forums
 * 29: sunday: create git project, prepare presentation etc.
 * 30: presentation!!!
 * 31. add approval checkbox+in db
 * 
 * 
 * 
 * 32. add votes to responses+add credibility based on votes 3
 * 33. add adapter of filters
 * 34. Add few more fields to profile and cloud
 * 35. add push to owner after each response
 * 36. add to browse number of potential viewers and possible recommandation which factors money to number of viewers
 * 37. Add share posts from other platforms by intent 
 * 38. add maximum money for details and suggestions to add ...
 * 39. assert that number of users gets updated after filter
 * 40. gps 2
 * 41. assert that query takes into account all additional data and tags 1
 * 
 * 1. Introduction and concept: 3 Min.
2. DEMO: 7 Min. *
3. Architecture: 2 min.
4. Feedback: 3 Min.

*: You must present a working demo. 
WHAT YOU NEED TO HAND IN:

- Link to your Github project repo.
- Project document: Team members (Names, emails), description of the project
- Installable APK



just before:
1. presentation (20 min )
2. vote (30 min)
3. add filters to filter screen and to queries (30 min)
4. add static gps (30 min)
if there is more time:
5. improve task view (20)
6. improve browse view (15)

 */

public class Post extends Activity implements OnClickListener {

	Button filter_button;
	Button submit_button;
	Button compensation_button;
	EditText description_edit;
	EditText title_edit;
	Task t;
	BuyEyeDAL tdd;
	TextView potentialViewers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		Parse.initialize(this, this.getString(R.string.parseApplication),
				this.getString(R.string.clientKey));
		tdd = new BuyEyeDAL(getApplicationContext(), ParseUser.getCurrentUser());
		//show possible tags:
		String[] possible_tags = getResources().getStringArray(R.array.possible_tags);
		
		potentialViewers=(TextView)findViewById(R.id.post_potential_viewers);
		
		int potentialUsers=tdd.getNumberOfUsers();
		if (potentialUsers!=-1)
			potentialViewers.setText(Integer.toString(potentialUsers)+" Users will get this task!");
		else 
			potentialViewers.setText("");
			
		MultiAutoCompleteTextView.CommaTokenizer tokenizer=new MultiAutoCompleteTextView.CommaTokenizer();
		final MultiAutoCompleteTextView tagsView = (MultiAutoCompleteTextView) this.findViewById(R.id.post_tags);
		ArrayAdapter<String> aaStr = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, possible_tags);
		tagsView.setAdapter(aaStr);
		tagsView.setTokenizer(tokenizer );
		tagsView.setTextColor(getResources().getColor(R.color.text_color));
		/*tagsView.setText("Predefined1,");tokenizer.terminateToken("Predefined1");*/
		
		t = new Task(ParseUser.getCurrentUser().getObjectId());
		compensation_button = (Button) findViewById(R.id.compensation);
		compensation_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DialogFragment newFragment = new CompensationDialog();
				newFragment.show(getFragmentManager(), "compensation");
			}
		});
		filter_button = (Button) findViewById(R.id.post_filter);
		filter_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						Filter.class);
				intent.putExtra("minAge", t.getMinAge());
				intent.putExtra("maxAge", t.getMaxAge());
				startActivityForResult(intent, 1337);
				// TODO: create gui and add data to db and cloud by DAL
			}
		});
		description_edit = (EditText) findViewById(R.id.editPost);
		description_edit.setOnClickListener(this);
		
		title_edit=(EditText)findViewById(R.id.post_title);
		title_edit.setOnClickListener(this);
		
		submit_button = (Button) findViewById(R.id.post_submit);
		submit_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				t.setDescription(description_edit.getText().toString());
				t.setTitle(title_edit.getText().toString());
				t.setTags(tagsView.getText().toString());
				tdd.insertTask(t);
			}
		});
		
		// Show the Up button in the action bar.
		// setupActionBar();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1337 && resultCode == RESULT_OK) {
			int maxAge = data.getIntExtra("maxAge", 100);
			int minAge = data.getIntExtra("minAge", 0);
			t.setMinAge(minAge);
			t.setMaxAge(maxAge);
			/*
			try {
				int viewers_num=t.getFilter().count();
				potentialViewers.setText(Integer.toString(viewers_num)+" Users will get this task!");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
			}*/
			/*
			t.getFilter().countInBackground(new CountCallback() {
				  public void done(int count, ParseException e) {
				    if (e == null) {
				    	potentialViewers.setText(Integer.toString(count)+" Users will get this task!");
				    } else {
				    	potentialViewers.setText("");
				    }
				  }
				});
			*/
			// add an adapter for the filters to appear on the screen
		}
	}
	
	@Override
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    ((EditText)v).setText("");
	    ((EditText)v).setTextColor(getResources().getColor(R.color.text_color));
	}
}
