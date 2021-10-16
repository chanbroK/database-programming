package model;

public class Borrower {
    private String customerName;
    private String loanNumber;

    public Borrower(String customerName, String loanNumber) {
        this.customerName = customerName;
        this.loanNumber = loanNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    @Override
    public String toString() {
        return customerName + "," + loanNumber;
    }
}