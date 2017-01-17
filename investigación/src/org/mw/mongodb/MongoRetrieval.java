package org.mw.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;

/**
 * 
 * https://www.tutorialspoint.com/mongodb/mongodb_java.htm
 * https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongo-java-driver/
 *   mongo-java-driver-3.2.2.jar or higher
 */
public class MongoRetrieval {

   public static void main( String args[] ) {

      try{
        String myUserName, myPassword;
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            
         // Now connect to your databases
         DB db = mongoClient.getDB( "test" );
         System.out.println("Connect to database successfully");
//         boolean auth = db.authenticate(myUserName, myPassword);
//         System.out.println("Authentication: "+auth);
//         DBCollection coll = db.createCollection("mycol2", new BasicDBObject());
//         System.out.println("Collection created successfully");

         DBCollection coll = db.getCollection("mycol");
         System.out.println("Collection mycol selected successfully");

         DBCursor cursor = coll.find();
         int i = 1;

         while (cursor.hasNext()) { 
            System.out.println("Document count: "+i); 
            System.out.println(cursor.next()); // display full document
            i++;
         }

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}