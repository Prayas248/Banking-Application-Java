package model;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

// Manages the list of all customers and handles validation during creation
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private ArrayList<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<Customer>();
        logger.info("CustomerService initialized | customer list ready");
    }

    // Validates inputs and adds a new customer to the list
    public void createUser(int id, String name, String email, String mobileNo) throws
            DuplicateCustomerException, InvalidEmailException, InvalidPhoneNumberException {

        logger.debug("createUser invoked | id={} | name={} | email={} | mobileNo={}", id, name, email, mobileNo);

        validateId(id);
        validateEmail(email);
        validateMobileNo(mobileNo);

        customers.add(new Customer(id, name, email, mobileNo));
        logger.info("Customer added successfully | id={} | name={} | totalCustomers={}", id, name, customers.size());

        System.out.println("Account creation successful");
        System.out.println(customers);
    }

    // Prints all customers (ID and name)
    public void showCustomers(){
        logger.debug("showCustomers invoked | totalCustomers={}", customers.size());
        for(int i=0;i<customers.size();i++){
            System.out.println(customers.get(i).getCustomerId() + ", " + customers.get(i).getCustomerName());
        }
    }

    // Prints all customers except the one with the given ID (used during transfers)
    public void showCustomersExcept(int excludeId) {
        logger.debug("showCustomersExcept invoked | excludeId={}", excludeId);
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId() != excludeId) {
                System.out.println(customers.get(i).getCustomerId() + ", " + customers.get(i).getCustomerName());
            }
        }
    }

    // Id Validation
    private void validateId(int id) throws DuplicateCustomerException {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                logger.warn("Validation failed - duplicate customer id | id={}", id);
                throw new DuplicateCustomerException("[ERROR] Duplicate Customer Id found");
            }
        }
    }

    // Email validation
    private void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@")) {
            logger.warn("Validation failed - invalid email | email={}", email);
            throw new InvalidEmailException("[ERROR] Invalid email: " + email);
        }
    }

    // Find a customer by their ID; throws if not found
    public Customer getCustomers(int id) {
        logger.debug("Lookup customer by id | id={}", id);
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                return c;
            }
        }
        logger.error("Customer lookup failed - id not found | id={}", id);
        throw new IndexOutOfBoundsException("Customer with ID " + id + " not found");
    }

    // Returns the most recently added customer
    public Customer getCustomers() {
        logger.debug("Fetching most recently added customer | totalCustomers={}", customers.size());
        return customers.get(customers.size() - 1);
    }

    // Mobile number validation
    private void validateMobileNo(String mobileNo) throws InvalidPhoneNumberException {
        if (!mobileNo.matches("[0-9]{10}")) {
            logger.warn("Validation failed - invalid mobile number | mobileNo={}", mobileNo);
            throw new InvalidPhoneNumberException("[ERROR] Phone number must be exactly 10 digits");
        }
    }
}