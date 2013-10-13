package com.buyeye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
 
public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);
 
        Parse.initialize(this, this.getString(R.string.parseApplication),
				this.getString(R.string.clientKey));
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        final EditText edtEmail=(EditText)findViewById(R.id.edtEmail);
        final EditText edtPassword=(EditText)findViewById(R.id.edtPassword);
        
        findViewById(R.id.btnLogin).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String email = edtEmail.getText().toString();
				String password = edtPassword.getText().toString();
				ParseUser.logInInBackground(email, password, new LogInCallback() {
					public void done(ParseUser user, ParseException e) {
						if (user != null) {
							Intent resultIntent = new Intent();	
							setResult(RESULT_OK, resultIntent);
							finish();
						} else {
							// TODO: handle failures
							// Signup failed. Look at the ParseException to
							// see what happened.
						}
					};
				});
				/*
				Intent resultIntent=getIntent();
				String email = edtEmail.getText().toString();
				String password = edtPassword.getText().toString();
				resultIntent.putExtra("email", email);
				resultIntent.putExtra("password", password);
				setResult(RESULT_OK, resultIntent);
				
				finish();
				*/
			}
		});
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
            	Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
    			//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	//startActivityForResult(intent, 1339);
                startActivityForResult(intent, 1340);
                //finish();
            }
        });
    }
    
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    		if (requestCode == 1340 && resultCode == RESULT_OK) {
    			Intent resultIntent = new Intent();	
				setResult(RESULT_OK, resultIntent);
				finish();
		}
		
    }
    
    
}
