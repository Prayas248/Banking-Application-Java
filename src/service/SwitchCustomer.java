package service;

import main.Main;
import model.BankAccount;
import model.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class SwitchCustomer {

    private static Logger logger = LoggerFactory.getLogger(SwitchCustomer.class);

    private static int customerCounter;
    public SwitchCustomer() {

    }
    public static void switchCustomerService(Scanner sc,CustomerService cs){
        logger.info("Switching customer service started");

        cs.showCustomers();
        System.out.println("Select customer ID to switch to: ");
        int switchID = sc.nextInt();

        logger.debug("Selecting customer ID to switch to switchId = {} ",switchID);

        try {
            Main.currCustomer = cs.getCustomers(switchID);
            Main.currAccount = Main.currCustomer.getFirstAccount();

            logger.info("Customer switched successfully to accountId = {} | customerId = {}",((BankAccount) Main.currAccount).getAccountNumber(),Main.currCustomer.getCustomerId());

            System.out.println("Switched to: " + Main.currCustomer.getCustomerName());
            if (Main.currAccount == null) {
                logger.warn("Account not found");
                System.out.println("No account found. Open an account using option 2.");
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error("Invalid index inserted");
            System.out.println("Customer not found.");
        }
    }
}
