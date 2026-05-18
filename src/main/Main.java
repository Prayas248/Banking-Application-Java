package main;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;
import model.Customer;
import model.CustomerService;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws InvalidPhoneNumberException, DuplicateCustomerException, InvalidEmailException {

        CustomerService cs = new CustomerService();
        cs.createUser(101, "ashwin", "a@b.com", "1234567890");
        cs.createUser(102, "ashwin", "a@b.com", "1234567890");
        cs.createUser(103, "ashwin", "a@b.com", "1234567890");

        cs.createUser(101, "ashwin", "a@b.com", "1234567890");
        cs.createUser(102, "ashwin", "a@b.com", "1234567890");
        cs.createUser(103, "ashwin", "a@b.com", "1234567890");
        System.out.println();
    }
}
