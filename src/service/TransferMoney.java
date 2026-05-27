package service;

import exception.AccountNotFound;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import main.Main;
import model.BankAccount;
import model.Customer;
import model.CustomerService;
import model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TransferMoney {

    private static final Logger logger = LoggerFactory.getLogger(TransferMoney.class);

    public TransferMoney() {

    }
    public static void transferMoneyService(Scanner sc,CustomerService cs){

        logger.info("Transfer Money Service Started");

        if (Main.currCustomer == null || Main.currAccount == null) {
            logger.warn("Customer or Account not found");
            System.out.println("Select a customer and account first.");
            return;
        }
        if (!(Main.currAccount instanceof BankAccount)) {
            logger.warn("Account type not valid for customerId = {}", Main.currCustomer.getCustomerId());
            System.out.println("Use Wallet Operations (option 6) for wallet transfers.");
            return;
        }
        else{
            System.out.println("Choose your account");
            try{
                Main.currAccount = Main.currCustomer.getAllAccounts();
                logger.info("Accounts Found for customerId = {}", Main.currCustomer.getCustomerId());
            }
            catch (AccountNotFound e){
                logger.error("Account not found for customerId={} ", Main.currCustomer.getCustomerId());
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Available customers:");
        cs.showCustomersExcept(Main.currCustomer.getCustomerId());
        System.out.println("Enter target customer ID: ");
        int targetId = sc.nextInt();

        logger.debug("Transfer Money Service targetId={} ", targetId);

        Customer targetCustomer;
        try {
            targetCustomer = cs.getCustomers(targetId);
            logger.info("Fetched Customer with customerId={} ", targetCustomer.getCustomerId());
        } catch (IndexOutOfBoundsException e) {
            logger.error("Customer not found");
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Choose Receiver's account");
        PaymentType targetPayment=null;

        try{
            targetPayment = targetCustomer.getAllAccounts();
            logger.info("Fetched Receiver's account with accountId = {}, customerId = {}",((BankAccount) targetPayment).getAccountNumber(),targetCustomer.getCustomerId());
        }
        catch (AccountNotFound e){
            logger.error("Account not found for receiver customer with customerId={} ", targetCustomer.getCustomerId());
            System.out.println(e.getMessage());
            return;
        }




        System.out.println("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        logger.debug("Transfer Money Service amount={} from customerId = {} to receiver with customerId = {}", amount,Main.currCustomer.getCustomerId(), targetCustomer.getCustomerId());

        try {
            ((BankAccount) Main.currAccount).transfer((BankAccount) targetPayment, amount);
            logger.info("Transfer Successful");
        } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
            logger.error("Transfer Failed");
            System.out.println(e.getMessage());
        }
    }
}
