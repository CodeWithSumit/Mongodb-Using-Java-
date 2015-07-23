package com.eCommerce.database.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class EcommerceDB {
	public static void main(String[] args) throws IOException{
		MongoClient mongoclient = new MongoClient("localhost",27017);
			DB db = mongoclient.getDB("E-Commerce");
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
			boolean flag = false;
			while(!flag)
			flag = authenticate(db,bufferedreader);
			
	}

	public static boolean authenticate(DB db, BufferedReader bufferedreader) throws IOException {
         boolean flag = true;
         System.out.println("user:");
         String user = bufferedreader.readLine();
         System.out.println("Password:");
         String pwd = bufferedreader.readLine();
         if(db.authenticate(user, pwd.toCharArray()))
         {   
        	 System.out.println("Which Product Category you want to insert");
              String ProdCategory =null;
              ProdCategory = bufferedreader.readLine();
              if(ProdCategory.equals("Clothes")) 
              	{
            	  DBCollection Clothescollection =db.getCollection("Clothes");
            	  clothes(bufferedreader,Clothescollection);
        	    }
              else if (ProdCategory.equals("Sports"))
              {
            	  DBCollection Sportscollection =db.getCollection("Sports");
            	  sports(bufferedreader,Sportscollection);
              }
              else if (ProdCategory.equals("Crockery"))
              {
            	  DBCollection Crockerycollection =db.getCollection("Crockery");
            	  crockery(bufferedreader,Crockerycollection);
              }
              else if (ProdCategory.equals("Electronics Gadgets"))
              {
            	  DBCollection Crockerycollection =db.getCollection("Electronics Gadgets");
            	  Electronics(bufferedreader,Crockerycollection);
              }
        	 String Command=null;
        	 while(true)
        	 {
        		 
        	 }
        	
         }
         return flag;
	}

	private static void Electronics(BufferedReader bufferedreader,
			DBCollection crockerycollection) {
		// TODO Auto-generated method stub
		
	}

	private static void crockery(BufferedReader bufferedreader,
			DBCollection crockerycollection) {
		// TODO Auto-generated method stub
		
	}

	private static void sports(BufferedReader bufferedreader,
			DBCollection sportscollection) {
		// TODO Auto-generated method stub
		
	}

	private static void clothes(BufferedReader bufferedreader,
			DBCollection Clothescollection) throws IOException {
		    String action = null;
	        System.out.println("Choose Your Action");
	        System.out.println("Insert");
	        System.out.println("Update");
	        System.out.println("Find");
	        System.out.println("Demand a Clothe");
	        action = bufferedreader.readLine();
	        if(action.equals("Insert")) InsertClothes(bufferedreader,Clothescollection);
	        	
	        	
	        
	        
	        
		
		
	}

	private static void InsertClothes(BufferedReader bufferedreader,
			DBCollection clothescollection) {
		BasicDBList dbl = new BasicDBList();
		BasicDBList db2 = new BasicDBList();
		BasicDBList db3 = new BasicDBList();
		dbl.add(new BasicDBObject("Color","Blue").append("Type", "Skinny Fit").append("Price", 1500));
		db2.add(new BasicDBObject("Color","Purple").append("Type", "Comfortable Fit").append("Price", 2000));
		db3.add(new BasicDBObject("Color","Black").append("Type", "Check").append("Price", 1000));
		//dbl.add(new BasicDBObject("Type", "Skinny Fit"));
		//dbl.add(new BasicDBObject("Price", 1500));
		//db2.add(new BasicDBObject("Color","Purple"));
		//db2.add(new BasicDBObject("Type", "Comfortable Fit"));
		//db2.add(new BasicDBObject("Price", 2000));
		//db3.add(new BasicDBObject("Color","Black"));
		//db3.add(new BasicDBObject("Type", "Checks"));
		//db3.add(new BasicDBObject("Price", 1000 ));
		db3.add(new BasicDBObject("Color","Red").append("Type", "Slim fit").append("Price", 1200));
		//db3.add(new BasicDBObject("Type", "Slim fit"));
		//db3.add(new BasicDBObject("Price", 1200));
		BasicDBObject MenObject =new BasicDBObject("Values", "Men")
	    .append("Jeans", dbl)
	    .append("Trouser", db2)
		.append("Shirt", db3);
		clothescollection.insert(MenObject);
		
	
	}

}
