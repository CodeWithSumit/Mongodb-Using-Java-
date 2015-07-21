package com.sumit.tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class stackOverflowQuestion {
	
	public static void main(String[] args) throws IOException
	{
		DB db = (new MongoClient("localhost", 27017)).getDB("Film");
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); 
		DBCollection dBCollection = db.getCollection("MyCollection");
		DBObject channelDBObject = new BasicDBObject(); 
		System.out.println("genre");
		String genre = bufferReader.readLine();
		String[] temp = genre.split(","); 
		int i=0; 
		BasicDBList genreDBList = new BasicDBList(); 
		DBObject genreDBObject = null; 
		while(i<temp.length){ 
			genreDBObject = new BasicDBObject(); 
			genreDBObject.put("genre",temp[i++]);
			genreDBList.add(genreDBObject);	
		} 
		channelDBObject.put("genre",genreDBList.toArray());
		System.out.println("Movie Id");
		String MovieId = bufferReader.readLine();
		channelDBObject.put("MovieId",Integer.parseInt(MovieId));
		dBCollection.insert(channelDBObject); 
		DBCursor dbcursor = dBCollection.find(); 
		while (dbcursor.hasNext())System.out.println(dbcursor.next()); 
	} 

		
}
