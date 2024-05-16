package view;

// Import necessary classes
import model.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TotalRevenueFileOutput class implements the Logger interface to log messages to a file.
 */
public class TotalRevenueFileOutput implements Logger {
    private PrintWriter logStream; // PrintWriter to write log messages to a file

    /**
     * Constructor initializes the logStream to write to "logSale.txt".
     * If the file cannot be opened for writing, an error message is printed.
     */
    public TotalRevenueFileOutput() {
        try {
            // Initialize PrintWriter to write to "logSale.txt" with auto-flush enabled
            logStream = new PrintWriter(new FileWriter("logSale.txt"), true);
        } catch (IOException ioe) {
            // Print error message if file cannot be opened
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace(); // Print stack trace for debugging
        }
    }

    /**
     * Logs the given message to the file.
     *
     * @param message The message to be logged.
     */
    @Override
    public void log(String message) {
        // Write the message to the log file
        logStream.println(message);
    }
}
