package view;

// Import necessary classes
import model.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LoggerException class implements the Logger interface to log exception messages to a file.
 */
public class LoggerException implements Logger {
    private PrintWriter logStream; // PrintWriter to write log messages to a file

    /**
     * Constructor initializes the logStream to write to "logException.txt".
     * If the file cannot be opened for writing, an error message is printed.
     */
    public LoggerException() {
        try {
            // Initialize PrintWriter to write to "logException.txt" with auto-flush enabled
            logStream = new PrintWriter(new FileWriter("logException.txt"), true);
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
