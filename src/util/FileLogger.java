package util;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.*;

public class FileLogger {
    private String filePath = "./Transactions/";

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
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}