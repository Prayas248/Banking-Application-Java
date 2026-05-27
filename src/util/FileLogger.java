package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Writes error messages to a per-customer log file under src/util/Transactions/
public class FileLogger {

    private static final Logger logger = LoggerFactory.getLogger(FileLogger.class);

    private String filePath = "./src/util/Transactions/";

    public FileLogger(Customer customer) {
        // Create the directory if it doesn't exist yet
        logger.info("File logger creation service invoked");
        if (!new File(filePath).exists()) {
            logger.info("File directory created");
            new File(filePath).mkdirs();
        }
        // Each customer gets their own log file named by their ID
        this.filePath += customer.getCustomerId() + ".txt";
        logger.info("File logger created for customerId={}", customer.getCustomerId());
    }

    // Appends an error message to the customer's log file
    public void log(String message) {
        logger.info("Logger invoked");
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
            out.println("[ERROR] " + message);
        } catch (IOException e) {
            logger.error("Failed to write log to file | filePath={} | message={}", filePath, message, e);
            System.err.println("IO Error occurred while logging message.");
        } catch (Exception e) {
            logger.error("Unexpected error while writing log to file | filePath={}", filePath, e);
            System.out.println("An unexpected error occurred while logging message.");
        }
    }
}