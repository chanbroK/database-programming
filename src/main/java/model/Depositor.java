package model;

public class Depositor {
    private String customerName;
    private String accountNumber;

    public Depositor(String customerName, String accountNumber) {
        super();
        this.customerName = customerName;
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return customerName + "," + accountNumber;
    }
}