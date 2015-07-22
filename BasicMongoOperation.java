package com.sumit.tutorial;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class BasicMongoOperations {

	public static void main(String[] args) throws IOException
	{    
		//Creating a connection to the Server
		MongoClient mongoClient = new MongoClient("localhost",27017);
		//Connecting to the database
		DB db = mongoClient.getDB("admin");
		//Getting a collection
		DBCollection coll = db.getCollection("MyColl");
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		//Inserting a Document
				BasicDBObject doc = new BasicDBObject("name", "Sumit")
				/*We use BasicDBObject whenever there is inner document embedded inside a document */
		        .append("EmpID", "415058")
		        .append("Domain", "Insurance")
		        .append("Project", new BasicDBObject("JH", "Tester").append("Cengea", "Developer"))
				.append("Roles",new BasicDBObject("Technology", "MSBI").append("Database", "NoSQL"));
		         coll.insert(doc);
		         //Inserting multiple user with same Domain,Project,Role
		         System.out.println("Enter the number of doc want to enter");
		         String EmpNum = bufferReader.readLine();
		         for(int i=1;i<=Integer.parseInt(EmpNum);i++)
		         {
		        	 System.out.println("Name");
		        	 String name = bufferReader.readLine();
		        	 System.out.println("Name");
		             String EmpId = bufferReader.readLine();
		             BasicDBObject docs = new BasicDBObject("name", name)
		     		/*We use BasicDBObject whenever there is inner document embedded inside a document */
		             .append("EmpID", Integer.parseInt(EmpId))
		             .append("Domain", "Insurance")
		             .append("Project", new BasicDBObject("JH", "Tester").append("Cengea", "Developer"))
		     		 .append("Roles",new BasicDBObject("Technology", "MSBI").append("Database", "NoSQL"));
		              coll.insert(docs);
		         }
		       //Finding first Document in collection
		         DBObject myDoc = coll.findOne();
		         System.out.println(myDoc);
		         //Counting a Document in a Collection
		         System.out.println(coll.getCount());
		         //Getting All Document 
		         DBCursor dbcursor = coll.find();
		         try
		         {
		        	 while(dbcursor.hasNext())
		        	 {
		        		 System.out.println(dbcursor.next());
		        	 }
		         }
		        	 finally
		        	 {
		        		 dbcursor.close(); 
		        	 }
		         //Getting a specific Document
		         BasicDBObject query = new BasicDBObject("name","Sumit");
		         DBCursor cursor = coll.find(query);
		         try
		         {
		        	 while(cursor.hasNext())
		        	 {
		        		 System.out.println(cursor.next());
		        	 }
		         }
		        	 finally
		        	 {
		        		 cursor.close();
		        	 } 
		         //Getting document on the basis of certain criteria 
		         BasicDBObject filterQuery = new BasicDBObject("Name",new BasicDBObject("$ne","History"))
		                                     .append("Max marks", new BasicDBObject("$gt",45) );
		         DBCursor cur =coll.find(filterQuery);
		         try
		         {
		        	 while(cur.hasNext())
		        	 {
		        		 System.out.println(cur.next());
		        	 }
		         }
		        	 finally
		        	 {
		        		 cur.close(); 
		        	 }
		 //Deletion of Specific document from the collection
		 System.out.println("Which Doc you want to delete");
         String name = bufferReader.readLine();
         DBObject mydoc = new BasicDBObject("Name",name);
         DBCursor delCur = coll.find(mydoc);
         try
         {
        	 while(delCur.hasNext())
        	 {
        		 coll.remove(mydoc);
        	 }
         }
         finally
         {
        	delCur.close();
         }
    }
	
}
         


	

