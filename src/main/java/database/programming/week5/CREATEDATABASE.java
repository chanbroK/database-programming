package database.programming.week5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CREATEDATABASE {
    public static void main(String[] args) throws Exception {
        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307", id, pw);
        // Each SQL can be executed with a Statement instance
        Statement sm = connection.createStatement();


        // DDL
//        sm.executeUpdate("CREATE DATABASE db");
        // 위의 코드는 중복 수행시 오류 발생

        // 방법 1. try, catch

        // 방법 2. CREATE OR REPLACE DATABASES
//        sm.executeUpdate("CREATE OR REPLACE DATABASE db");
        // 위의 코드는 DATABASE가 초기화됨 (삭제 후 생성하기 때문에)

        // 방법 3. CREATE DATABASE IF NOT EXISTS
//        sm.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
//        System.out.println("[Warning]" + sm.getWarnings());
        // 위의 코드는 Warning 발생
        // CHARACTER SET, COLLATE 의 Default는 latin 이기 때문에 한글 입력이 되지 않는다.

        // 방법 4. CREATE DATABASE IF NOT EXISTS CHARACTER SET , COLLATE
        sm.executeUpdate("CREATE DATABASE IF NOT EXISTS db CHARACTER SET = 'euckr' COLLATE ='euckr_Korean_ci' COMMENT='WELCOME TO DATABASE'");
        System.out.println("[Warning]" + sm.getWarnings());
    }
}
