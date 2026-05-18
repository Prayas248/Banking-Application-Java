package util;

import model.Customer;

import java.io.FileNotFoundException;

public class TestFile {

    public static void main(String[] args) {

        Customer customer = new Customer(
                1,
                "John Doe",
                "john.doe@example.com",
                "123-456-7890"
        );

        FileLogger logger = new FileLogger(customer);

        try {
            logger.log("This is a test message.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}