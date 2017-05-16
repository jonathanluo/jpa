package org.mw.nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * 
 * https://www.tutorialspoint.com/mongodb/mongodb_java.htm
 * https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongo-java-driver/
 *  mongo-java-driver-3.4.2.jar or higher

    Authentication 
    http://mongodb.github.io/mongo-java-driver/3.0/driver/reference/connecting/authenticating/
    http://www.programcreek.com/java-api-examples/index.php?api=com.mongodb.MongoCredential 
 */
public class MongoRetrieve {

   public static void connection1() {
       try{
         String user = "dcfg";
         char[] pwd = "dconfig".toCharArray();
         String database = "test";
         MongoCredential credential = MongoCredential.createCredential(user, database, pwd); //createScramSha1Credential

         MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
         DB db = mongoClient.getDB( "test" );
         System.out.println("Connect to database successfully");

         DBCollection collection = db.getCollection("restaurants");
         System.out.println("Collection restaurants selected successfully");

         DBCursor cursor = collection.find();
         System.out.println("================= show all documents =================");
         printDocuments(cursor);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      } finally {

      }
   }

   public static void connection2() {
       try{

         MongoClientURI uri = new MongoClientURI("mongodb://dcfg:dconfig@localhost:27017/?authSource=test");

         MongoClient mongoClient = new MongoClient(uri);
         DB db = mongoClient.getDB( "test" );
         System.out.println("Connect to database successfully");

         DBCollection collection = db.getCollection("restaurants");
         System.out.println("Collection restaurants selected successfully");

         DBCursor cursor = collection.find();
         System.out.println("================= show all documents =================");
         printDocuments(cursor);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      } finally {

      }
   }

   public static void connection3() {
       try{

         MongoClientURI uri = new MongoClientURI("mongodb://dcfg:dconfig@localhost:27017/?authSource=test");

         MongoClient mongoClient = new MongoClient(uri);
         MongoDatabase db = mongoClient.getDatabase( "test" );
         System.out.println("Connect to database successfully");

         MongoCollection collection = db.getCollection("restaurants");
         System.out.println("Collection restaurants selected successfully");

         FindIterable fi = collection.find();
         System.out.println("================= show all documents =================");
         printDocuments(fi.iterator());

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      } finally {

      }
   }

   public static void main( String args[] ) {
       connection1();
       connection2();
       connection3();
   }

   private static void printDocuments(DBCursor cursor) {
       int i = 1;
       while (cursor.hasNext()) { 
          System.out.println("Document: " + i); 
          System.out.println(cursor.next()); 
          i++;
       }
   }

   private static void printDocuments(MongoCursor cursor) {
       int i = 1;
       while (cursor.hasNext()) { 
          System.out.println("Document: " + i); 
          System.out.println(cursor.next()); 
          i++;
       }
   }
}