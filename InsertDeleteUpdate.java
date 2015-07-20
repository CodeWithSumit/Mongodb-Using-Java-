package com.MongoDB.sumit.tutorial;

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
import com.mongodb.util.JSON;

public class Driver {
	
		public static void main(String[] args) throws IOException{
			DB db = (new MongoClient("localhost", 27017)).getDB("test");
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
			boolean flag = false;
			while(!flag) flag = authenticate(db,bufferReader);
		}
              public static boolean authenticate(DB db,BufferedReader bufferReader) throws IOException{
            	  boolean flag = true;
            	  System.out.println("User: ");
            	  String user = bufferReader.readLine();
            	  System.out.println("Password: ");
            	  String pwd = bufferReader.readLine();
            	 if( db.authenticate(user, pwd.toCharArray())){
            		 DBCollection dBCollection = db.getCollection("MyTable");
            		 String Command = null;
            		 while(true){
            			 System.out.println("what do you want...");
            			 Command = bufferReader.readLine();
            			 if(Command.equals("exit")) break;
            			 else if(Command.equals("findAll")) findAll(dBCollection);
            			 else if(Command.equals("insertJSON")) insertJSON(bufferReader,dBCollection);
            			 else if(Command.equals("insert")) insert(bufferReader,dBCollection);
            			 else if(Command.equals("delete")) delete(bufferReader,dBCollection);
            			 else if(Command.equals("update")) update(bufferReader,dBCollection);


            		 }
            	 }
            	 else
            	 {
            		 System.out.println("invalid user/ Password......");
            		 flag = false;
            	 }
            	  return flag;
              }
			
			private static void update(BufferedReader bufferReader,
					DBCollection dBCollection) throws IOException {
				System.out.println("Update from name: ");
				DBObject fromDBObject = new BasicDBObject();
				fromDBObject.put("name",bufferReader.readLine() );
				System.out.println("Update to name: ");
				DBObject toDBObject = new BasicDBObject();
				toDBObject.put("name",bufferReader.readLine() );
				DBObject updateDBObject = new BasicDBObject();
				updateDBObject.put("$set", toDBObject);
				dBCollection.update(fromDBObject, updateDBObject);
			}
			private static void delete(BufferedReader bufferReader,
					DBCollection dBCollection) throws IOException {
				System.out.println("What?");
				DBObject basicDBObject = new BasicDBObject();
				basicDBObject.put("name",bufferReader.readLine() );
				dBCollection.remove(basicDBObject);
			}
			private static void insert(BufferedReader bufferReader,
					DBCollection dBCollection) throws IOException {
				System.out.println("channel name");
				String name = bufferReader.readLine();
				DBObject channelDBObject = new BasicDBObject();
				channelDBObject.put("name",name);
				System.out.println("channel Subscription: ");
				String Subscription = bufferReader.readLine();
				channelDBObject.put("subscription",Integer.parseInt(Subscription));
				System.out.println("channel Tutorial: ");
				String tutorials = bufferReader.readLine();
				String[] temp = tutorials.split(",");
				int i=0;
				BasicDBList tutorialDBList = new BasicDBList();
				DBObject tutorialDBObject = null;
				while(i<temp.length){
					tutorialDBObject = new BasicDBObject();
					tutorialDBObject.put("name",temp[i++]);
					tutorialDBList.add(tutorialDBObject);
				}
				channelDBObject.put("tutorial",tutorialDBList.toArray());
				dBCollection.insert(channelDBObject);
			}
			private static void findAll(DBCollection dBCollection) {
				DBCursor dbcursor = dBCollection.find();
				while (dbcursor.hasNext())System.out.println(dbcursor.next());
				
			}
			private static void insertJSON(BufferedReader bufferReader,DBCollection dBCollection) throws IOException {
				System.out.println("JSON:");
				dBCollection.insert((DBObject) JSON.parse(bufferReader.readLine()));
				
			}
}
