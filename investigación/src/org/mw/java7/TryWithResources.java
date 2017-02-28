package org.mw.java7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.oracle.tutorial.jdbc.JDBCTutorialUtilities;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/enhancements.html#javase7
 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html
 */
public class TryWithResources {

    static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void writeToFileZipFileContents(String zipFileName, String outputFileName)
            throws java.io.IOException {

        java.nio.charset.Charset charset = java.nio.charset.Charset.forName("US-ASCII");
        java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

        // Open zip file and create output file with try-with-resources statement

        // In this example, the try-with-resources statement contains two declarations that are separated by a semicolon; 
        // ZipFile and BufferedWriter. When the block of code that directly follows it terminates, either normally or 
        // because of an exception, the close methods of the BufferedWriter and ZipFile objects are automatically called 
        // in this order. Note that the close methods of resources are called in the opposite order of their creation.
        try (
          java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);
          java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
        ) {

          // Enumerate each entry

          for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements();) {

            // Get the entry name and write it to the output file

            String newLine = System.getProperty("line.separator");
            String zipEntryName = ((java.util.zip.ZipEntry)entries.nextElement()).getName() + newLine;
            writer.write(zipEntryName, 0, zipEntryName.length());
          }
        }
    }

    public static void viewTable(Connection con) throws SQLException {

        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

        try (Statement stmt = con.createStatement()) {

          ResultSet rs = stmt.executeQuery(query); // rs.close()?
          while (rs.next()) {
            String coffeeName = rs.getString("COF_NAME");
            int supplierID = rs.getInt("SUP_ID");
            float price = rs.getFloat("PRICE");
            int sales = rs.getInt("SALES");
            int total = rs.getInt("TOTAL");
            System.out.println(coffeeName + ", " + supplierID + ", " + price +
                               ", " + sales + ", " + total);
          }

        } catch (SQLException e) {
          JDBCTutorialUtilities.printSQLException(e);
        }
      }
}
