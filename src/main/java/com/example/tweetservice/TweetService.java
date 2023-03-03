package com.example.tweetservice;

import com.google.gson.*; //parses favs.json in src folder to find Tweet and structure JSON data into classes
import java.io.*; //handling files
import java.util.regex.*; //for regex urls in Tweet's text
// SpringBoot framework imports
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetService {
	
	private Tweet[] allTweets = readJsonFile(); //all tweets will be stored in here at start 
	
	public Tweet[] getTweets() {
		return allTweets;
	}
	
	public Tweet[] readJsonFile(){
		GsonBuilder build = new GsonBuilder(); //necessary to create Gson object
		build.setPrettyPrinting();
		Gson formatter = build.create(); //create Gson formatter
		File file = new File("src/favs.json"); //json file in src
		FileReader reader = null;
		try {//ensure favs.json file is found in src folder
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArray tweets = (JsonArray)JsonParser.parseReader(reader); //get tweets in array
		String json = tweets.toString(); //convert tweets to String
		return formatter.fromJson(json, Tweet[].class);
	}
	@RequestMapping(value="/allTweets", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAllTweets() throws IOException {
		String json = "["; // preparing to hold array of json formatted Tweets
		String pattern="{ \"id\":\"%s\", \"created_at\":\"%s\", \"text\":\"%s\" \n}"; //json pattern
		for(Tweet t: allTweets) {//for each tweet in alltweets
			json += String.format(pattern, t.getId(), t.getCreated_at(), t.getText()); //apply pattern to tweet attributes
			json += ","; //separator
		}
		json = json.substring(0, json.length() - 1); //remove extra comma
		json+="]"; //end of array of tweets
		return json;
	}
	
	@RequestMapping(value="/tweetLinks", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAllExternalLinks() throws IOException{
		String json = "[";// expecting array of tweets
		String idJson = "{\"id\":\"%s\", "; //Formats Tweet id
		String text; 
		String url = "\"urls\": ["; //Formats urls field
		String urlJson = "\"%s\","; //Formats individual urls in the urls field
		
		//this regex expression was sourced from StackOverflow:
		//StackOverflow Link: https://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java
		String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE); //generate url pattern
		for(Tweet t: allTweets) {//For each Tweet in the alltweets 
			text = t.getText(); //get text field from Tweet
			json += String.format(idJson, t.getId()); //add tweet id to json
			json += url; // urls field added
			Matcher match = p.matcher(text);// find all text that match regex (this would be urls)
			while(match.find()) {//for every url found
				json += String.format(urlJson, match.group()); //add it to json
			}
			json += "] },"; //end of urls + end of tweet 
		}
		return json; // return json
	}
	@RequestMapping(value="/tweet", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getTweetDetails(long id) throws IOException {
		//given tweet id, return created_at , text, screen_name, lang
		String json = "";
		String pattern="{ \"created_at\":\"%s\", \"text\":\"%s\", \"screen_name\": \"%s\", \"lang\": \"%s\" } "; 
		for(Tweet t: allTweets) {//search for the tweet with the given id
			if(t.getId() == id) {// if found
				json = String.format(pattern, t.getCreated_at(), t.getText(), t.getUser().getScreen_name(), t.getLang());
				break;
			}
		}
		if(json.length() == 0) {//if no tweet with id is found, return empty json
			json += "{}";
		}
		return json; 
	}
	@RequestMapping(value="/profile", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getUserProfile(String screenName) throws IOException {
		//given user screenName, return name, location, and description
		String json = "";
		String pattern="{ \"name\":\"%s\", \"location\":\"%s\", \"description\": \"%s\", \"lang\": \"%s\" } "; 
		for(Tweet t: allTweets) {//search for the tweet for the user with the given screen_name
			if(t.getUser().getScreen_name().equals(screenName)) {//if screen name found
				json = String.format(pattern, t.getUser().getName(), t.getUser().getLocation(), t.getUser().getDescription(), t.getLang());
				break; //Tweet with User information has been found
			}
		}
		if(json.length() == 0) {//if no user found, return empty json
			json += "{}";
		}
		return json;
	}
}
