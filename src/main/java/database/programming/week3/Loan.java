package database.programming.week3;

public class Loan {
    private String loanNumber;
    private String branchName;
    private String amount;

    public Loan(String loanNumber, String branchName, String amount) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String toString() {
        return loanNumber + "," + branchName + "," + amount;
    }
}
