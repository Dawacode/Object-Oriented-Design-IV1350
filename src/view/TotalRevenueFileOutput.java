package view;
import model.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements Logger {
 private PrintWriter logStream;

         public TotalRevenueFileOutput() {
         try {
             logStream = new PrintWriter(
                     new FileWriter("log.txt"), true);

             } catch (IOException ioe) {
             System.out.println("CAN NOT LOG.");
             ioe.printStackTrace();
             }
         }

         public void log(String message) {
        logStream.println(message);
         }



 }
