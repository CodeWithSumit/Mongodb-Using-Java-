package com.sumit.tutorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            		 DBCollection dBCollection = db.getCollection("mesaage");
            		 String Command = null;
            		 while(true){
            			 System.out.println("what do you want...");
            			 Command = bufferReader.readLine();
            			 if(Command.equals("exit")) break;
            			 else if(Command.equals("findAll")) findAll(dBCollection);
            			 else if(Command.equals("inserJSON")) insertJSON(bufferReader,dBCollection);
            		 }
            	 }
            	 else
            	 {
            		 System.out.println("invalid user/ Password......");
            		 flag = false;
            	 }
            	  return flag;
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
