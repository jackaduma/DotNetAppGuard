package mk.DotNetApp.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author makun
 *
 */
public class XmlParser {
	private static final String TAG = "XmlParser";
	
	private String xmlFilePath = null;
	private Document dom = null;
	public Element rootElement = null;
	
	public XmlParser() {
		
	}
	
	public XmlParser(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	public void init() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.dom = builder.parse(new File(this.xmlFilePath));
//		System.out.println(dom.getChildNodes().item(0).getNodeName());
		this.rootElement = dom.getDocumentElement();
	}
	
	public String getElementAttribute(Element element, String attrName) {
		return element.getAttribute(attrName);
	}
	
	public String getRootAttribute(String attrName) {
		return rootElement.getAttribute(attrName);
	}
	
	public List<Element> getElement(Element root, String name) {
		List<Element> elements = new ArrayList<Element>();
		NodeList nodeList = root.getElementsByTagName(name);
		for (int i = 0; i <nodeList.getLength(); i ++) {
			Node node = nodeList.item(i);
			elements.add((Element) node);
		}
		return elements;
	}
	
	public String getElementValue(Element element) {
		NodeList nodeList = element.getChildNodes();
		int len = nodeList.getLength();
		for (int i = 0; i < len; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.TEXT_NODE)
				return node.getNodeValue();
		}
		return null;
	}
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		String testFile = "E:\\MK_Projects\\WinPhoneAppGuard\\Win8Metro\\GoEverywhere_v1_2089\\AppxManifest.xml";
		XmlParser xmlParser = new XmlParser(testFile);
		xmlParser.init();
		List<Element> list = xmlParser.getElement(xmlParser.rootElement, "Capability");
		for (Element element : list) {
			System.out.println(xmlParser.getElementAttribute(element, "Name"));
		}		
	}
}
