package org.mw.nosql.redis;

import redis.clients.jedis.Jedis; 
import java.util.*;

public class RedisKey { 
   public static void main(String[] args) { 
      //Connecting to Redis server on localhost 
      Jedis jedis = new Jedis("localhost"); 
      System.out.println("Connection to server sucessfully"); 
      //store data in redis list 
      // Get the stored data and print it 
      Set<String> list = jedis.keys("*"); 
      Iterator<String> it = list.iterator();
      while (it.hasNext()) {
          System.out.println("List of stored keys:: " + it.next()); 
    	  
      }
//      for(int i = 0; i<list.size(); i++) { 
//         System.out.println("List of stored keys:: "+list.get(i)); 
//      } 
   } 
}

