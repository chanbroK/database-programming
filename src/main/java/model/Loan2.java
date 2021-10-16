package model;

public class Loan2 {
    private String loanNumber;
    private String branchName;
    private int amount;
    private String date;

    public Loan2(String loanNumber, String branchName, int amount, String date) {
        this.loanNumber = loanNumber;
        this.branchName = branchName;
        this.amount = amount;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return loanNumber + "," + branchName + "," + amount;
    }
}