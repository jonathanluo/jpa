package org.mw.nosql.redis;
import redis.clients.jedis.Jedis; 

/**
 * https://www.tutorialspoint.com/redis/redis_java.htm
 * https://mvnrepository.com/artifact/redis.clients/jedis/2.9.0
 *
 */
public class Redis { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //check whether server is running or not 
      System.out.println("Server is running: "+jedis.ping()); 
   } 
}
