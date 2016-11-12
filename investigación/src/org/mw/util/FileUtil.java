package org.mw.util;

import java.io.File;
import java.util.Scanner;

public class FileUtil {

    public static void printFile(String filename) {
        Scanner input = null;
        try {
            input = new Scanner(new File(filename));
            while (input.hasNextLine()) {
               System.out.println(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static String getContent(String filename) {
        StringBuilder sb = new StringBuilder();
        Scanner input = null;
        try {
            input = new Scanner(new File(filename));
            while (input.hasNextLine()) {
               sb.append(input.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return sb.toString();
    }
}
