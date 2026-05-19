package model;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;

import java.util.*;

public class CustomerService {
    private ArrayList<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<Customer>();
    }

    public void createUser(int id, String name, String email, String mobileNo) throws
            DuplicateCustomerException, InvalidEmailException, InvalidPhoneNumberException {

        validateId(id);
        validateEmail(email);
        validateMobileNo(mobileNo);

        customers.add(new Customer(id, name, email, mobileNo));
        System.out.println("Account creation successful");
        System.out.println(customers);
    }

    public void showCustomers(){
        for(int i=0;i<customers.size();i++){
            System.out.println((customers.get(i).getCustomerId())+1 + ", " + customers.get(i).getCustomerName());
        }
    }


    // Id Validation
    private void validateId(int id) throws DuplicateCustomerException {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                throw new DuplicateCustomerException("[ERROR] Duplicate Customer Id found");
            }
        }
    }

    // Email validation
    private void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@")) {
            throw new InvalidEmailException("[ERROR] Invalid email: " + email);
        }
    }

    public Customer getCustomers(int id) {
        System.out.println(customers);
        return customers.get(id-1);
    }

    public Customer getCustomers() {
        System.out.println(customers);
        return customers.get(customers.size() - 1);
    }

    // Mobile number validation
    private void validateMobileNo(String mobileNo) throws InvalidPhoneNumberException {
        if (!mobileNo.matches("[0-9]{10}")) {
            throw new InvalidPhoneNumberException("[ERROR] Phone number must be exactly 10 digits");
        }

    }


}
