package service;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;
import main.Main;
import model.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class CreateCustomer {

    private static final Logger logger = LoggerFactory.getLogger(CreateCustomer.class);

    private static int customerCounter;
    public CreateCustomer() {

    }
    public static void creationOfCustomerService(Scanner sc,CustomerService cs){

        logger.info("Creation of customer started");

        System.out.println("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your phone no: ");
        String ph = sc.nextLine();

        logger.debug("Received information for username = {} | id={}", name, customerCounter);

        try {
            cs.createUser(customerCounter, name, email, ph);
            logger.info("User created successfully for username = {} | id={}", name, customerCounter);
            // Set the newly created customer as the active customer
            Main.currCustomer = cs.getCustomers();
            customerCounter++;
        } catch (InvalidEmailException | DuplicateCustomerException | InvalidPhoneNumberException e) {
            logger.error("Invalid email or phone number while creating customer for username = {} | id={}", name, customerCounter);
            System.out.println(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error encountered during customer creation for username = {} | id={}", name, customerCounter);
            System.out.println(e.getMessage());
        }
    }
}
