package model;

public class Customer {
    private final int CustomerId;
    private String CustomerName;
    private String CustomerEmail;
    private String CustomerPhoneNumber;

    public Customer(int CustomerId,String CustomerName, String CustomerEmail, String CustomerPhoneNumber) {
        this.CustomerId = CustomerId;
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomerPhoneNumber = CustomerPhoneNumber;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return CustomerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        CustomerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + CustomerId +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerEmail='" + CustomerEmail + '\'' +
                ", CustomerPhoneNumber='" + CustomerPhoneNumber + '\'' +
                '}';
    }
}
