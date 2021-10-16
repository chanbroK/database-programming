package model;

public class PtWorks {
    private String employeeName;
    private String branchName;
    private int salary;

    public PtWorks(String employeeName, String branchName, int salary) {
        super();
        this.employeeName = employeeName;
        this.branchName = branchName;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return employeeName + "," + branchName + "," + salary;
    }
}
