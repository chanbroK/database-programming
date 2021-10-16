package model;

public class Customer {
    private String customer;
    private String customerStreet;
    private String customerCity;

    public Customer(String customer, String customerStreet, String customerCity) {
        super();
        this.customer = customer;
        this.customerStreet = customerStreet;
        this.customerCity = customerCity;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    @Override
    public String toString() {
        return customer + "," + customerStreet + "," + customerCity;
    }

}
