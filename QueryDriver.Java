package com.sumit.tutorial;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;

public class QueryDriver {
	public static void main(String[] args) throws UnknownHostException{
		
	    DB db = (new MongoClient("localhost", 27017)).getDB("test");
	    DBCollection dBCollection = db.getCollection("mesaage");
	    BasicDBObject basicDbOjects = new BasicDBObject();
	    basicDbOjects.put("name","Anuj");
	    DBCursor dbcursor = dBCollection.find(basicDbOjects);
	    while (dbcursor.hasNext())System.out.println(dbcursor.next());
	}
}
