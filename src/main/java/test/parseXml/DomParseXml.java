package test.parseXml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by lifeng on 16/7/16.
 */
public class DomParseXml {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        HashMap hashMap = new HashMap(8,1.0F);
        hashMap.put(1,1);
        hashMap.put(2,1);
        hashMap.put(3,1);
        hashMap.put(4,1);
        hashMap.put(5,1);
        hashMap.put(6,1);
        hashMap.put(7,1);
        hashMap.put(8,1);
        hashMap.put(9,1);
        //实例化一个工厂类
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);//名称空间支持
        //构造解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = ClassLoader.getSystemResourceAsStream("book.xml");
        //传入流对象并获得document对象
        Document document = builder.parse(is);
        //获得根节点对象
        Element element = document.getDocumentElement();
        System.out.println(element.getNamespaceURI());
        //获得根节点的子节点,也可以获得任意节点
        NodeList bookNodes = element.getElementsByTagName("book");
        //遍历book节点的子节点
        for (int i = 0; i < bookNodes.getLength(); i++) {
            Element bookElement = (Element) bookNodes.item(i);
            //获得books的子节点book名为id的属性
            System.out.println("id: " + bookElement.getAttribute("id"));
            //获得book的子节点
            NodeList childNode = bookElement.getChildNodes();
            for (int j = 0; j < childNode.getLength(); j++) {
                //判读子节点的属性,book的子节点常见的有三种
                //文本类型:例如换行符 \n TEXT_NODE
                //注释类型:尖括号感叹号打头的 <!--注释--> COMMENT_NODE
                //元素类型:标签定义的元素 <price> ELEMENT_NODE
                System.out.println(childNode.item(j) instanceof Element);
                if (childNode.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //获得叶子节点的值
                    System.out.println(childNode.item(j).getFirstChild().getNodeValue());
                    //获得子节点的属性
                    NamedNodeMap map = childNode.item(j).getAttributes();
                    //遍历输出属性
                    for (int k = 0; k < map.getLength(); k++) {
                        Node node = map.item(k);
                        if (node != null) {
                            System.out.println(node.getNodeValue());
                        }
                    }
                }
            }
        }
    }
}
