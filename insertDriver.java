# Mongodb-Using-Java-
This Repository basically aimed to learn and explore mongodb using java 
package com.sumit.tutorial;

import java.net.UnknownHostException;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;


public class insertDriver {
	
	public static void main(String[] args) throws UnknownHostException{
		
    DB db = (new MongoClient("localhost", 27017)).getDB("test");
    DBCollection dBCollection = db.getCollection("mesaage");
    BasicDBObject basicDbOjects = new BasicDBObject();
    basicDbOjects.put("name","Sumit");
    basicDbOjects.put("EmpId","415058");
    dBCollection.insert(basicDbOjects);
	    
	    }
}
