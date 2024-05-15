package view;

import model.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerException implements Logger {
    private PrintWriter logStream;


    public LoggerException() {

        try {
            logStream = new PrintWriter(
                    new FileWriter("logException.txt"), true);

        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        logStream.println(message);

    }
}
