package com.example.tweetservice;

public class User {//Holds all necessary user information for the REST API GET Requests
	private String name;
	private String screen_name;
	private String location;
	private String description; 
	// All User fields are accessible from Tweet object 
	// User fields align with fields found in favs.json
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
