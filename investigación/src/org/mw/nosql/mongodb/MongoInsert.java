package org.mw.nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;

import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * https://www.tutorialspoint.com/mongodb/mongodb_java.htm
 * https://oss.sonatype.org/content/repositories/releases/org/mongodb/mongo-java-driver/
 *   mongo-java-driver-3.2.2.jar or higher
 */
public class MongoInsert {

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
		System.out.println("Collection restaurants selected successfully");

		BasicDBObject doc = new BasicDBObject("title", "MongoDB Java").
		append("description", "NOSQL database").
		append("likes", 96d).
		append("url", "https://www.tutorialspoint.com/mongodb/mongodb_java.htm").
		append("by", "tutorials point").
		append("note", "inserted by Java code");
		coll.insert(doc);

		Map<String, Object> map = new HashMap<>();
		map.clear();
		map.put("title", "Math Calculus I");
		map.put("description", "Derivities");
		map.put("by", "Charles Hoton");
		map.put("url", "http://www.youtube.com");
		map.put("likes", 600.0d);
		map.put("note","inserted by Java code");
		doc = new BasicDBObject(map);
		coll.insert(doc);

		map.clear();
		map.put("title", "MongoDB Overview");
		map.put("description", "MongoDB is no sql database");
		map.put("by", "Yale Press");
		map.put("url", "http://yalebooks.com/");
		map.put("likes", 231.8d);
		map.put("note","inserted by Java code");
		doc = new BasicDBObject(map);
		coll.insert(doc);

		map.clear();
		map.put("title", "Eclipse Neon");
		map.put("description", "Eclipse Neon IDE");
		map.put("by", "Eclipse Communities");
		map.put("url", "http://www.eclipse.org");
		map.put("likes", 178.3d);
		map.put("note","inserted by Java code");
		doc = new BasicDBObject(map);
		coll.insert(doc);

		map.clear();
		map.put("title", "2016 Re-ALBUM");
		map.put("description", "2016 music");
		map.put("by", "Sechs Kies");
		map.put("url", "http://www.SechsKies.com");
		map.put("likes", 28.6d);
		map.put("note","inserted by Java code");
		doc = new BasicDBObject(map);
		coll.insert(doc);

		System.out.println("Collection document inserted successfully");
      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}