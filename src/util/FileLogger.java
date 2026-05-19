package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.*;

// Writes error messages to a per-customer log file under src/util/Transactions/
public class FileLogger {
    private String filePath = "./src/util/Transactions/";

    public FileLogger(Customer customer) {
        // Create the directory if it doesn't exist yet
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
        // Each customer gets their own log file named by their ID
        this.filePath += customer.getCustomerId() + ".txt";
    }

    // Appends an error message to the customer's log file
    public void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
            out.println("[ERROR] " + message);
        } catch (IOException e) {
            System.err.println("IO Error occurred while logging message.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while logging message.");
        }
    }
}