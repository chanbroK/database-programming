package model;

public class Loan {
    private String loanNumber;
    private String branchName;
    private int amount;

    public Loan(String loanNumber, String branchName, int amount) {
        this.loanNumber = loanNumber;
        this.branchName = branchName;
        this.amount = amount;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return loanNumber + "," + branchName + "," + amount;
    }
}
