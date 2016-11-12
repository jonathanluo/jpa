package org.mw.util;

import java.io.File;
import java.util.Scanner;

public class FileUtil {

    public static void printFile(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            while (input.hasNextLine()) {
               System.out.println(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
