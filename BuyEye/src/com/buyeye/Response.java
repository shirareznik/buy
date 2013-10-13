package com.buyeye;

public class Response {
	String response;
	String responderID;
	String taskId;
	String id;
	int likes;
	boolean isClicked;
	
	public boolean isClicked() {
		return isClicked;
	}
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Response(String response, String responderID, String taskId) {
		super();
		this.response = response;
		this.responderID = responderID;
		this.taskId = taskId;
	}
	public Response() {
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponderID() {
		return responderID;
	}
	public void setResponderID(String responderID) {
		this.responderID = responderID;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
