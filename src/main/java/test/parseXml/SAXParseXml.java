package test.parseXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lifeng on 16/8/7.
 */
public class SAXParseXml {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        //实例化一个工厂类
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //构造解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = ClassLoader.getSystemResourceAsStream("book.xml");
        //传入流对象并获得document对象
        Document document = builder.parse(is);
        //获得根节点对象
        Element element = document.getDocumentElement();
    }
}
