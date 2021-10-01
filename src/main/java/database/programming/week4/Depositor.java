package database.programming.week4;

public class Depositor {
    private final String customerName;
    private final String accountName;

    public Depositor(String customerName, String accountName) {
        this.customerName = customerName;
        this.accountName = accountName;
    }

    public String toString() {
        return "Depositor{" +
                "customerName='" + customerName + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public String getAccountName() {
        return accountName;
    }
}
