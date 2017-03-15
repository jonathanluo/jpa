package org.mw.nosql.mongodb;

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

import org.apache.commons.lang3.SerializationUtils;

/**
 * 
 * https://www.tutorialspoint.com/mongodb/mongodb_java.htm
 * https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongo-java-driver/
 *   mongo-java-driver-3.2.2.jar or higher
 */
public class MongoUpdate {

   public static void main( String args[] ) {

      try{

         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

         // Now connect to your databases
         DB db = mongoClient.getDB( "test" ); //MongoDatabase db = mongoClient.getDatabase( "test" );
         System.out.println("Connect to database successfully");

//         boolean auth = db.authenticate(myUserName, myPassword);
//         System.out.println("Authentication: "+auth);   

         DBCollection coll = db.getCollection("mycol");
         System.out.println("Collection mycol selected successfully");

         // show documents before update
         System.out.println("================= show documents before update =================");
         DBCursor cursor = coll.find();
         printDocuments(cursor);

         // perform the updates
         cursor = coll.find();
         while (cursor.hasNext()) { 
            DBObject dbObj = cursor.next();
            if (dbObj instanceof BasicDBObject) {
                BasicDBObject origDoc = (BasicDBObject)dbObj;
                BasicDBObject newDoc = SerializationUtils.clone(origDoc);
                Object likes = origDoc.get("likes");
                if (likes instanceof Double) {
                    Double value = (Double)likes;
                    newDoc.put("likes", 200.0 + value.doubleValue());
                }
                coll.update(origDoc, newDoc); 
            }
         }

         System.out.println("Document updated successfully");
         System.out.println("================= show documents after update =================");
         // show documents after update
         cursor = coll.find();
         printDocuments(cursor);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
   
   private static void printDocuments(DBCursor cursor) {
       int i = 1;
       while (cursor.hasNext()) { 
          System.out.println("Document: "+i); 
          System.out.println(cursor.next()); 
          i++;
       }
   }
}