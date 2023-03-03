package com.example.tweetservice;

public class Tweet {//holds necessary Tweet data found in favs.json
	
	private long id; 
	private String created_at; 
	private String text; 
	private User user; //see User class
	private String lang; 
	// Variables align with the field names found in favs.json
	public Tweet() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

}
