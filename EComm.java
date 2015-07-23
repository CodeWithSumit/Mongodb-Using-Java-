package com.eCommerce.database.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class ecommerceDB {
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
        	 System.out.println("Which Product you want to insert");
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
        	 String Command=null;
        	 while(true)
        	 {
        		 
        	 }
        	
         }
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
			DBCollection Clothescollection) {
		
		
	}

}
