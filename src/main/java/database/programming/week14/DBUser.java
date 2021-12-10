package database.programming.week14;

import java.util.Date;

public class DBUser implements java.io.Serializable {

    private int userId;
    private String username;
    private String createdBy;
    private Date createdDate;

    public DBUser() {

    }

    public DBUser(int userId, String username, String createdBy, Date createdDate) {
        super();
        this.userId = userId;
        this.username = username;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String toString() {
        return username + " " + createdBy + " " + userId + " " + createdDate;
    }
}

