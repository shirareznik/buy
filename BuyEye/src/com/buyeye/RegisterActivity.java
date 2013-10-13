package com.buyeye;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SignUpCallback;
 
public class RegisterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_register);
 
        final EditText edtEmail=(EditText)findViewById(R.id.reg_email);
        final EditText edtPassword=(EditText)findViewById(R.id.reg_password);
        
        findViewById(R.id.btnRegister).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String email = edtEmail.getText().toString();
				String password = edtPassword.getText().toString();
				//TODO: check not empty?
				ParseUser user = new ParseUser();
				user.setUsername(email);
				user.setPassword(password);
				user.setEmail(email);
				//user.put("phone", "650-253-0000");
				user.signUpInBackground(new SignUpCallback() {
				  public void done(com.parse.ParseException e) {
				    if (e == null) {
						ParseInstallation.getCurrentInstallation().saveInBackground();
						// Saving the device's owner
						ParseInstallation installation = ParseInstallation
								.getCurrentInstallation();
						installation.put("owner", ParseUser.getCurrentUser());
						installation.saveInBackground();
				    	Intent resultIntent = new Intent();	
						setResult(RESULT_OK, resultIntent);
						finish();
				    } else {
				    }
				  }});

			
				/*
				Intent resultIntent=getIntent();
				String email = edtEmail.getText().toString();
				String password = edtPassword.getText().toString();
				resultIntent.putExtra("reg_email", email);
				resultIntent.putExtra("reg_password", password);
				setResult(RESULT_OK, resultIntent);
				finish();
				*/
			}
		});
        
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                              
                // Switching to Login Screen/closing register screen
            	Intent resultIntent = new Intent();	
				setResult(RESULT_CANCELED, resultIntent);
				finish();
            }
        });
    }
}
