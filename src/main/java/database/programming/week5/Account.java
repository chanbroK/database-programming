package database.programming.week5;

public class Account {
    private final String accountNumber;
    private final String branchName;
    private final int balance;

    public Account(String accountNumber, String branchName, int balance) {
        this.accountNumber = accountNumber;
        this.branchName = branchName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", branchName='" + branchName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
