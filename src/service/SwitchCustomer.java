package service;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;
import main.Main;
import model.CustomerService;

import java.util.Scanner;


public class SwitchCustomer {
    private static int customerCounter;
    public SwitchCustomer() {

    }
    public static void switchCustomerService(Scanner sc,CustomerService cs){
        cs.showCustomers();
        System.out.println("Select customer ID to switch to: ");
        int switchID = sc.nextInt();
        try {
            Main.currCustomer = cs.getCustomers(switchID);
            Main.currAccount = Main.currCustomer.getFirstAccount();
            System.out.println("Switched to: " + Main.currCustomer.getCustomerName());
            if (Main.currAccount == null) {
                System.out.println("No account found. Open an account using option 2.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Customer not found.");
        }
    }
}
