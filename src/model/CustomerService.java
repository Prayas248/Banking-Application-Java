package model;

import exception.DuplicateCustomerException;
import exception.InvalidEmailException;
import exception.InvalidPhoneNumberException;

import java.util.*;

public class CustomerService {
    private ArrayList<Customer> customers;
    private Boolean idValidation;
    private Boolean emailValidation;
    private Boolean phoneValidation;

    public CustomerService(){
        customers = new ArrayList<Customer>();
//        customers.add(new Customer(0,null,null,null));
//        System.out.println("constructor is called");
    }

    public void createUser(int id, String name, String email, String mobileNo) throws
            DuplicateCustomerException, InvalidEmailException, InvalidPhoneNumberException {

        idValidation = validateId(id);
        emailValidation = validateEmail(email);
        phoneValidation = validateMobileNo(mobileNo);
//        System.out.println(customers.isEmpty());
//        System.out.println(idValidation +" "+ emailValidation +" "+ phoneValidation);

        if (customers.isEmpty() && emailValidation && phoneValidation){
            customers.add(new Customer(id, name, email, mobileNo));
            System.out.println("Account creation successful");
        }
        else if (idValidation && emailValidation && phoneValidation){
            customers.add(new Customer(id, name, email, mobileNo));
            System.out.println("Account creation successful");
        }
    }

    // Id Validation
    private Boolean validateId(int id){
        boolean localCheck = true;
            for(Customer c : customers){
                try{
                    if(c.getCustomerId() == id){
                        localCheck = false;
                        throw new DuplicateCustomerException("[ERROR] Duplicate Customer Id found");
                    }
                }
                catch (DuplicateCustomerException e) {
                    System.out.println(e);
                }
            }

        if (localCheck)
            return true;
        else
            return false;
    }

    // Email validation
    private Boolean validateEmail(String email) throws InvalidEmailException {
        try{
            if (email.contains("@"))
                return true;
            else
                throw new InvalidEmailException("Invalid email");
        }
        catch (InvalidEmailException e){
            System.out.println(e);
        }
        return  false;
    }

    // Mobile number validation
    private Boolean validateMobileNo(String mobileNo) throws InvalidPhoneNumberException {
       Boolean containsAlabets = mobileNo.matches("^[0-9]");
       if(!containsAlabets){
           try{
               if (mobileNo.trim().length() <10 || mobileNo.trim().length() > 10){
                   throw new InvalidPhoneNumberException("Phone number should be 10 digits ");
               }
               else{
                   return true;
               }
           }
           catch (InvalidPhoneNumberException e){
               System.out.println(e);
           }
       }
        return false;
    }


}
