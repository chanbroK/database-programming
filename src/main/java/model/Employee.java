package model;

public class Employee {
    private String employeeName;
    private String street;
    private String city;

    public Employee(String employeeName, String street, String city) {
        super();
        this.employeeName = employeeName;
        this.street = street;
        this.city = city;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return employeeName + "," + street + "," + city;
    }
}
