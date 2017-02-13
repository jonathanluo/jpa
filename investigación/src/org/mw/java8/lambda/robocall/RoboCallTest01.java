package org.mw.java8.lambda.robocall;

import java.util.List;

/*
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section1
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/examples/RoboCallExample.zip
 *
 * @author MikeW
 */
public class RoboCallTest01 {
  
  public static void main(String[] args) {
    
    List<Person> pl = Person.createShortList();
    RoboContactMethods robo = new RoboContactMethods();

    System.out.println("\n==== Test 01 ====");
    System.out.println("\n=== Calling all Drivers ===");
    robo.callDrivers(pl);

    System.out.println("\n=== Emailing all Draftees ===");
    robo.emailDraftees(pl);

    System.out.println("\n=== Mail all Pilots ===");
    robo.mailPilots(pl);

  }

}
