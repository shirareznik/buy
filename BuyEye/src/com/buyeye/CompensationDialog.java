package com.buyeye;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class CompensationDialog extends DialogFragment 
{
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	       
	    	   Context context = getActivity().getApplicationContext();
	    	  
	    	    // get the layout inflater
	    	    LayoutInflater li = (LayoutInflater) context
	    	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	    // inflate our custom layout for the dialog to a View
	    	    View view = li.inflate(R.layout.activity_dark, null);
	    	  	    	
	    	// Use the Builder class for convenient dialog construction
	       
	    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    	  // inform the dialog it has a custom View
    	    builder.setView(view);
    	    // and if you need to call some method of the class
    	    final NumberPicker np = (NumberPicker) view
    	            .findViewById(R.id.compensationPicker);
    	    np.setMaxValue(100);
    	    np.setMinValue(1);
    	 
	    	builder.setMessage(R.string.selectCompensation)
	               .setPositiveButton(R.string.compensationOK, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	  ((Post)getActivity()).t.setCompensation(np.getValue());
	                	   //Post.this.t.setCompensation(np.getValue());
	                	  
	                   }
	               })
	               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	
}
