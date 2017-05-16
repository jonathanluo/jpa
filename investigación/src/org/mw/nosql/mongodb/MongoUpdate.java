package org.mw.nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

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

		String user = "dcfg";
		char[] pwd = "dconfig".toCharArray();
		String database = "test";
		MongoCredential credential = MongoCredential.createCredential(user, database, pwd); //createScramSha1Credential
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
		DB db = mongoClient.getDB( "test" );
		System.out.println("Connect to database successfully");

        DBCollection coll = db.getCollection("restaurants");

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

      } catch(Exception e){
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