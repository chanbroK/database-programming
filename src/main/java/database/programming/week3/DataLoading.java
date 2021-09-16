package database.programming.week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoading {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("D:\\project\\database-programming\\src\\main\\resources\\loan.txt"));

        ArrayList<Loan> loanList = new ArrayList<>();
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            String[] resultArr = line.split(",");
            Loan loan = new Loan(resultArr[0], resultArr[1], resultArr[2]);
            loanList.add(loan);
        }

        for (Loan loan : loanList) {
            System.out.println(loan);
        }
        r = new BufferedReader(new FileReader("D:\\project\\database-programming\\src\\main\\resources\\borrower.txt"));
        ArrayList<Borrower> borrowerList = new ArrayList<>();
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            String[] resultArr = line.split("    ");
            Borrower borrower = new Borrower(resultArr[0], resultArr[1]);
            borrowerList.add(borrower);
        }

        for (Borrower borrower : borrowerList) {
            System.out.println(borrower);
        }
    }
}