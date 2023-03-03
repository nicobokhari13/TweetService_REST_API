# Tweet Service API by Nico Bokhari 

## Overview 

This REST API responds to GET requests for Tweet data from a JSON file that is within the project. This API was implemented with Java using the SpringBoot Framework. Additionally, the Gson Library, as well as a URL regex expression was used to facilitate easier data retrieval and storage of the JSON data file. 

## How to Run Project

Assumptions: Eclipse with SpringTools Suite is installed. 

Tools to Interact with API: A user can interact with this API in the browser, command-line with curl, or Postman. Anyone of these solutions will produce identical results.

### Run Server 

In Eclipse, open the project. 

Open TweetApiApplication.java in your editor. 

Right Click anywhere on the file, navigate to "Run As" > Spring Boot App 

Now the SpringBoot server is running with the API

### Interact with Server

In the browser, Postman, or command line, send a GET request to localhost:8080.

The following GET Mappings are available: 
  
* /allTweets
  * Get all tweets, but only their id, create time, and text
* /tweetLinks
  * Get a list of all tweets, but only their id and a list of links within the tweet's text
* /tweet
  * Parameter: id
  * Given a tweet id, get its create time, text, user's screen_name, and language
* /profile
  * Parameter: screenName
  * Given a screen name, get the user's name, location, and description

