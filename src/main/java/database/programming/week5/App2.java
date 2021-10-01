package database.programming.week5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App2 {
    public static void main(String[] args) throws Exception {
        // read File
        BufferedReader r = new BufferedReader(new FileReader("D:\\project\\personal\\database-programming\\src\\main\\resources\\account.xml"));
        String text = "";
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            text += line + "\n";
        }
        r.close();

        // XMLParsing
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(text)));

        // toString
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        // source -> result convert
        tf.transform(source, result);
        System.out.println(writer);

        // getElement
        NodeList nodeList = doc.getElementsByTagName("account");
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element account = (Element) node;
            //Element는 Node를 implement 한다.
            Element accountNumber = (Element) account.getElementsByTagName("account_number").item(0);
            String accountNumberString = accountNumber.getTextContent();

            String branchName = account.getElementsByTagName("branch_name").item(0).getTextContent();
            int balance = Integer.parseInt(account.getElementsByTagName("balance").item(0).getTextContent());
            accountList.add(new Account(accountNumberString, branchName, balance));
        }
        System.out.println(accountList);

        // Save in DB
        Connection connection = DriverManager.getConnection("jdbc:maiadb://localhost:3307", "root", "1234");
        Statement statement = connection.createStatement();

        // DDL
        statement.executeUpdate("CREATE DATABASE account");
        statement.executeUpdate("USE account");
        statement.executeUpdate("CREATE TABLE account(account_number VARCHAR(50),branch_name VARCHAR(50), balance VARCHAR(50))");

        // DML

    }
}
