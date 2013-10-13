package com.buyeye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

public class ProfileActivity extends Activity {
	NumberPicker agePicker;
	MultiAutoCompleteTextView profileTags;
	  private RadioGroup radioSexGroup;
	  private RadioButton radioSexButton;
	  private Spinner education;
	ParseUser currentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// GENERAL:
		// set user and login:
		Parse.initialize(this, this.getString(R.string.parseApplication),
				this.getString(R.string.clientKey));
		// TODO: add while loop over current user
		if (ParseUser.getCurrentUser() == null) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			Intent intent1 = new Intent(this, ProfileActivity.class);
			startActivity(intent1);
		} else {
			currentUser = ParseUser.getCurrentUser();
		}
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		// Saving the device's owner
		ParseInstallation installation = ParseInstallation
				.getCurrentInstallation();
		installation.put("owner", ParseUser.getCurrentUser());
		installation.saveInBackground();
		
		
		String[] possible_tags = getResources().getStringArray(R.array.possible_tags);
		MultiAutoCompleteTextView.CommaTokenizer tokenizer=new MultiAutoCompleteTextView.CommaTokenizer();
		final MultiAutoCompleteTextView profileTags = (MultiAutoCompleteTextView) this.findViewById(R.id.profile_tags);
		ArrayAdapter<String> aaStr = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, possible_tags);
		profileTags.setAdapter(aaStr);
		profileTags.setTokenizer(tokenizer );
		profileTags.setTextColor(getResources().getColor(R.color.text_color));
		
		radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
		education=(Spinner)findViewById(R.id.profile_education);
		
		
		agePicker = (NumberPicker)findViewById(R.id.profile_age);
		agePicker.setMinValue(10);
		agePicker.setMaxValue(120);
		
		
		
		findViewById(R.id.profile_btnSubmit).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
								        // get selected radio button from radioGroup
						int selectedId = radioSexGroup.getCheckedRadioButtonId();
					        radioSexButton = (RadioButton)findViewById(selectedId);
			
				ParseInstallation installation = ParseInstallation.getCurrentInstallation();
				
				installation.put("age",agePicker.getValue());
				installation.put("tags", profileTags.getText().toString());
				installation.put("sex",radioSexButton.getText());
				installation.put("education", String.valueOf(education.getSelectedItem()));
				try{
				installation.save();
				}catch(ParseException e){};
				Toast.makeText(ProfileActivity.this, "Profile updated!",Toast.LENGTH_SHORT).show();
				//installation.saveInBackground();
				/*if (taskName == null || "".equals(taskName)) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
				*/
					
					
				//}
			}
		});
		
		/*
        findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

					setResult(RESULT_CANCELED);
					finish();
			}
		});
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}
}
