package service;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;
import main.Main;
import model.CustomerService;

import java.util.Scanner;


public class CreateCustomer {
    private static int customerCounter;
    public CreateCustomer() {

    }
    public static void creationOfCustomerService(Scanner sc,CustomerService cs){
        System.out.println("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your phone no: ");
        String ph = sc.nextLine();
        try {
            cs.createUser(customerCounter, name, email, ph);
            // Set the newly created customer as the active customer
            Main.currCustomer = cs.getCustomers();
            customerCounter++;
        } catch (InvalidEmailException | DuplicateCustomerException | InvalidPhoneNumberException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
