package database.programming.week13;

import org.hibernate.Session;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + MariaDB");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        DBUser user = new DBUser();

        user.setUserId(300);
        user.setUsername("superman");
        user.setCreatedBy("system");
        user.setCreatedDate(new Date());

        session.save(user);
        session.getTransaction().commit();

        session.close();
    }
}
