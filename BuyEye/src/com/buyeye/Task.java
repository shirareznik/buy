package com.buyeye;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseQuery;

public class Task {
	String ownerID;
	String description;
	//type requested- text, at the beginning. after- maybe image
	int minAge;
	int maxAge;
	int minVote;
	int compensation;
	String title;
	String tags;
	String id;
	ParseQuery<ParseInstallation> filter=ParseInstallation.getQuery();
	
	//to show in list: title, compensation, number of possible viewers, number of answers, open/closed, maybe- number of views
	// possibly- add compensation for
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Task(String ownerID, String description, int minAge, int maxAge,
			int minVote, int compensation, String title, String tags, Context context) {
		super();
		Parse.initialize(context, context.getString(R.string.parseApplication),
				context.getString(R.string.clientKey));
		filter=ParseInstallation.getQuery();
		this.ownerID = ownerID;
		this.description = description;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.minVote = minVote;
		this.compensation = compensation;
		this.title = title;
		this.tags = tags;
	}

	public Task(String ownerID, String description, int minAge, int maxAge,
			int minVote, int compensation, String title, String tags) {
		super();
		  
		//Parse.initialize(this, "voAEyoNWc4grAqcvxmVuObkyLWbFTnD4huflMygv","KlBSivKd62QGyf9BDXSDWnvK3NKwUotZMfYCMdFK");
		this.filter=ParseInstallation.getQuery();
		this.ownerID = ownerID;
		this.description = description;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.minVote = minVote;
		this.compensation = compensation;
		this.title = title;
		this.tags = tags;
	}
	
	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Task()
	{
		
	}
	
	public Task(String ownerID) {
		super();
		this.ownerID = ownerID;
		this.minAge=0;
		this.maxAge=200;
	}




	public Task(String ownerID, String description, int minAge, int maxAge,
			int minVote, int compensation) {
		super();
		this.ownerID = ownerID;
		this.description = description;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.minVote = minVote;
		this.compensation = compensation;
	}
	
	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public int getMinAge() {
		return minAge;
	}




	public void setMinAge(int minAge) {
		this.minAge = minAge;
		this.filter.whereGreaterThanOrEqualTo("age", this.minAge);
	}




	public int getMaxAge() {
		return maxAge;
	}




	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		this.filter.whereLessThanOrEqualTo("age", this.maxAge);
	}




	public ParseQuery getFilter() {
		return filter;
	}

	public void setFilter(ParseQuery filter) {
		this.filter = filter;
	}

	public int getMinVote() {
		return minVote;
	}




	public void setMinVote(int minVote) {
		this.minVote = minVote;
	}




	public int getCompensation() {
		return compensation;
	}




	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}

	/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
*/




	
}
