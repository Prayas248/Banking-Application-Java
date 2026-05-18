package util;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.*;

public class FileLogger {
    private String filePath = "./src/util/Transactions/";

    public FileLogger(Customer customer) {
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
        this.filePath += customer.getCustomerId() + ".txt";
    }

    public void log(String message) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
            out.println("[ERROR] " + message);
        } catch (IOException e) {
            System.err.println("I/O error occurred while logging message.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred.");
            // e.printStackTrace();
        }
    }
}