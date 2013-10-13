package com.buyeye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;

public class Filter extends Activity {

	NumberPicker minPicker,maxPicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		minPicker = (NumberPicker)findViewById(R.id.min_age_picker);
		maxPicker= (NumberPicker)findViewById(R.id.max_age_picker);
		Intent i=getIntent();
		minPicker.setMinValue(10);
		minPicker.setMaxValue(120);
		maxPicker.setMinValue(10);
		maxPicker.setMaxValue(120);
		minPicker.setValue(i.getIntExtra("minAge", 10));
		maxPicker.setValue(i.getIntExtra("maxAge", 100));
		findViewById(R.id.btnOK).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				
				int minAge = minPicker.getValue();
				int maxAge = maxPicker.getValue();
				/*if (taskName == null || "".equals(taskName)) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
				*/
					Intent resultIntent = new Intent();
					resultIntent.putExtra("minAge", minAge);
					resultIntent.putExtra("maxAge", maxAge);	
					setResult(RESULT_OK, resultIntent);
					finish();
				//}
			}
		});
		
		/*
		findViewById(R.id.btnOK).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//EditText edtTaskName = (EditText)findViewById(R.id.edtNewItem);
				DatePicker dp=(DatePicker)findViewById(R.id.datePicker1);
				//Calendar calendar = Calendar.getInstance();
			    //calendar.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

				
				String taskName = edtTaskName.getText().toString();
				if (taskName == null || "".equals(taskName)) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra("title", taskName);

					resultIntent.putExtra("dueDate", calendar.getTime());
					setResult(RESULT_OK, resultIntent);
					finish();
				}
			}
		});
		*/
        findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

					setResult(RESULT_CANCELED);
					finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		return true;
	}


	
	
}
