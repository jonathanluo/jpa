package org.mw.lang;

import java.util.ArrayList;

/**
 * http://stackoverflow.com/questions/184710/what-is-the-difference-between-a-deep-copy-and-a-shallow-copy
 *
 */
public class ShallowAndDeepCopy {

    public static void main(String args[]) throws ClassNotFoundException, CloneNotSupportedException{

      System.out.println(" == = shallow copy  == =");

      ArrayList<Language> list=new ArrayList<Language>();
      list.add(new Language("C"));
      list.add(new Language("JAVA"));

      ArrayList<Language> shallow=(ArrayList<Language>) list.clone();
      //We used here clone since this always shallow copied.

      System.out.println(list == shallow);

      for(int i=0;i<list.size();i++) {
    	  System.out.println(list.get(i) == shallow.get(i));//true
      }

      System.out.println("\n == = deep copy  == =");

      ArrayList<Language> deep=new ArrayList<Language>();
      for(Language language:list){
          deep.add((Language) language.clone());
      }
      System.out.println(list == deep);
      for(int i=0;i<list.size();i++) {
          System.out.println(list.get(i) == deep.get(i));//false
      }

	} 
}
