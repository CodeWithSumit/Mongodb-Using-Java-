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
        	 System.out.println("Which Product Category you want to choose");
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
	        System.out.println("MultipleInsert");
	        System.out.println("Update");
	        System.out.println("Find");
	        System.out.println("Demand a Clothe");
	        action = bufferedreader.readLine();
	        if(action.equals("Insert")) InsertClothes(bufferedreader,Clothescollection);
	        else if (action.equals("MultipleInsert")) MultipleInsert(bufferedreader,Clothescollection);
	}
	

	private static void InsertClothes(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		/*Mens Section*/
		BasicDBList dbl = new BasicDBList();
		BasicDBList db2 = new BasicDBList();
		BasicDBList db3 = new BasicDBList();
		dbl.add(new BasicDBObject("Color","Blue").append("Type", "Skinny Fit").append("Price", 1500));
		db2.add(new BasicDBObject("Color","Purple").append("Type", "Comfortable Fit").append("Price", 2000));
		db3.add(new BasicDBObject("Color","Black").append("Type", "Check").append("Price", 1000));
		db3.add(new BasicDBObject("Color","Red").append("Type", "Slim fit").append("Price", 1200));
		BasicDBObject MenObject =new BasicDBObject("Category", "Men")
	    .append("Jeans", dbl)
	    .append("Trouser", db2)
		.append("Shirt", db3);
		clothescollection.insert(MenObject);
		/*Womens Section*/
		BasicDBList dbW = new BasicDBList();
		BasicDBList dbWo = new BasicDBList();
		BasicDBList dbWom = new BasicDBList();
		BasicDBList dbWomen = new BasicDBList();
		dbW.add(new BasicDBObject("Color","Blue").append("Type", "Skinny Fit").append("Price", 500));
		dbWo.add(new BasicDBObject("Color","Purple").append("Type", "Comfortable Fit").append("Price", 200));
		dbWom.add(new BasicDBObject("Color","Black").append("Type", "Check").append("Price", 100));
		dbWom.add(new BasicDBObject("Color","Red").append("Type", "Slim fit").append("Price", 1200));
		dbWomen.add(new BasicDBObject("Color","Black").append("Type", "Stylish").append("Price", 700));
		BasicDBObject WomenObject =new BasicDBObject("Category", "Women")
	    .append("Jeans", dbW)
	    .append("Trouser", dbWo)
		.append("Shirt", dbWom)
		.append("Suit",dbWomen);
		clothescollection.insert(WomenObject);
		/*Kids Section*/
		BasicDBList dbK = new BasicDBList();
		BasicDBList dbKi = new BasicDBList();
		dbK.add(new BasicDBObject("Color","Blue").append("Type", "Short").append("Price", 500));
		dbKi.add(new BasicDBObject("Color","Green").append("Type", "Comfortable").append("Price", 200));
		BasicDBObject KidsObject =new BasicDBObject("Category", "Kids")
	    .append("Jeans", dbK)
	    .append("Trouser", dbKi);
		clothescollection.insert(KidsObject);
		System.out.println("Would you like to continue");
		System.out.println("Yes");
		System.out.println("No");
		String Response = null;
		Response=bufferedreader.readLine();
		if(Response.equals("Yes"))
		clothes(bufferedreader,clothescollection);
		else if(Response.equals("No"))
		{
			System.out.println("Want to Switch Some other Product");
			System.out.println("Yes");
			System.out.println("No");
			String switchResponse = bufferedreader.readLine();
			
		}
	}
	public static void MultipleInsert(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
	System.out.println("Select the Gender for which you want to make insertion");
	String Gender = null;
	System.out.println("1.Men");
	System.out.println("2.Women");
	System.out.println("3.Kids");
	Gender = bufferedreader.readLine();
	if(Gender.equals("Men")) 
	{    
		String Product = null;
		System.out.println("Choose the item you want to insert");
		System.out.println("Jeans");
		System.out.println("Shirt");
		System.out.println("Trouser");
		Product = bufferedreader.readLine();
	    if(Product.equals("Jeans")) insertJeans(bufferedreader,clothescollection);
	    else if(Product.equals("Shirt")) insertShirt(bufferedreader,clothescollection);
	    else if(Product.equals("Trouser")) insertTrouser(bufferedreader,clothescollection);
		
	}
	else if(Gender.equals("Women"))
	{
		String Product = null;
		System.out.println("Choose the item you want to insert");
		System.out.println("Jeans");
		System.out.println("Shirt");
		System.out.println("Skirt");
		Product = bufferedreader.readLine();
	    if(Product.equals("Jeans")) insertWomenJeans(bufferedreader,clothescollection);
	    else if(Product.equals("Shirt")) insertWomenShirt(bufferedreader,clothescollection);
	    else if(Product.equals("Skirt")) insertWomenSkirt(bufferedreader,clothescollection);
	}
}

	private static void insertWomenSkirt(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of SKirt");
		String QtyShirt = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(QtyShirt);i++)
		{
			String Color[] ={"Black","Blue","Red","Green","yellow","orange","Multicolor"};
			String Type[] ={"Platted","Non Platted",};
			System.out.println("Enter the price of Skirt");
			String Price = bufferedreader.readLine();
			BasicDBList skirtList = new BasicDBList();
			skirtList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range", Integer.parseInt(Price)));
			BasicDBObject MultiskirtObject =new BasicDBObject("Category", "Women")
			.append("Skirt", skirtList);
			clothescollection.insert(MultiskirtObject);
		}
	 
		
	}

	private static void insertWomenShirt(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of Shirt");
		String QtyShirt = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(QtyShirt);i++)
		{
			String Color[] ={"Black","Blue","Red","Green","yellow","orange","Multicolor"};
			String Type[] ={"Formal","Casual","Full Sleves Formal",};
			System.out.println("Enter the price of Shirt");
			String Price = bufferedreader.readLine();
			BasicDBList shirtList = new BasicDBList();
			shirtList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range", Integer.parseInt(Price)));
			BasicDBObject MultishirtObject =new BasicDBObject("Category", "Women")
			.append("Shirt", shirtList);
			clothescollection.insert(MultishirtObject);
		}
		
	}

	private static void insertWomenJeans(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of jean");
		String Quantity = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(Quantity);i++)
		{
			String Color[] ={"Black","Blue"};
			String Type[] ={"Skinny Fit","Comfortable","High waist"};
			System.out.println("Enter the price of Jean");
			String Price = bufferedreader.readLine();
			BasicDBList jeanList = new BasicDBList();
			jeanList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range",Integer.parseInt(Price)));
			BasicDBObject MultijeanObject =new BasicDBObject("Category", "Women")
			.append("Jeans", jeanList);
			clothescollection.insert(MultijeanObject);
		}
		
	}

	private static void insertTrouser(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of Trouser");
		String QtyTrs = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(QtyTrs);i++)
		{
			String Color[] ={"Black","Blue","Red","Green","yellow","orange","Multicolor"};
			String Type[] ={"Fitting","Comfortable","3 in 1",};
			System.out.println("Enter the price of Trouser");
			String Price = bufferedreader.readLine();
			BasicDBList trsList = new BasicDBList();
			trsList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range", Integer.parseInt(Price)));
			BasicDBObject MultiTrsObject =new BasicDBObject("Category", "Men")
			.append("Jeans", trsList);
			clothescollection.insert(MultiTrsObject);
		}	
		
	}

	private static void insertShirt(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of Shirt");
		String QtyShirt = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(QtyShirt);i++)
		{
			String Color[] ={"Black","Blue","Red","Green","yellow","orange","Multicolor"};
			String Type[] ={"Check","Full sleves","Half Sleves",};
			System.out.println("Enter the price of Shirt");
			String Price = bufferedreader.readLine();
			BasicDBList shirtList = new BasicDBList();
			shirtList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range", Integer.parseInt(Price)));
			BasicDBObject MultishirtObject =new BasicDBObject("Category", "Men")
			.append("Shirt", shirtList);
			clothescollection.insert(MultishirtObject);
		}
	 
		
	}

	private static void insertJeans(BufferedReader bufferedreader,
			DBCollection clothescollection) throws IOException {
		System.out.println("Enter the quantity of jean");
		String Quantity = bufferedreader.readLine();
		for(int i=1;i<=Integer.parseInt(Quantity);i++)
		{
			String Color[] ={"Black","Blue"};
			String Type[] ={"Skinny Fit","Comfortable","Well Bottom"};
			System.out.println("Enter the price of Jean");
			String Price = bufferedreader.readLine();
			BasicDBList jeanList = new BasicDBList();
			jeanList.add(new BasicDBObject("Color",Color).append("Type", Type).append("Price Range",Integer.parseInt(Price)));
			BasicDBObject MultijeanObject =new BasicDBObject("Category", "Men")
			.append("Jeans", jeanList);
			clothescollection.insert(MultijeanObject);
		}
		
		
	}

}
