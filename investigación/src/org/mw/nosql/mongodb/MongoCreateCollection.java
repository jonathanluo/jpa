package org.mw.nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;

import com.mongodb.ServerAddress;
import java.util.Arrays;

/**
 * 
 * https://www.tutorialspoint.com/mongodb/mongodb_java.htm
 * https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongo-java-driver/
 *   mongo-java-driver-3.2.2.jar or higher
 */
public class MongoCreateCollection {

   public static void main( String args[] ) {

      try{
		String user = "dcfg";
		char[] pwd = "dconfig".toCharArray();
		String database = "test";
		MongoCredential credential = MongoCredential.createCredential(user, database, pwd); //createScramSha1Credential

		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
		DB db = mongoClient.getDB( "test" );
		System.out.println("Connect to database successfully");

		DBCollection coll = db.createCollection("mycol2", new BasicDBObject());
		System.out.println("Collection created successfully");
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}