package database.programming.week5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class App {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("account");
        doc.appendChild(root);
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element accountNumber = doc.createElement("account_number");
        accountNumber.setAttribute("xsi:type", "xsd:string");
        accountNumber.setTextContent("A-101");

        Element branchName = doc.createElement("branch_name");
        branchName.setAttribute("xsi:type", "xsd:string");
        branchName.setTextContent("Downtown");

        Element balance = doc.createElement("balance");
        balance.setAttribute("xsi:type", "xsd:integer");
        balance.setTextContent("500");

        root.appendChild(accountNumber);
        root.appendChild(branchName);
        root.appendChild(balance);

        //toString
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        // source -> result convert
        tf.transform(source, result);
        System.out.println(writer);
    }
}
