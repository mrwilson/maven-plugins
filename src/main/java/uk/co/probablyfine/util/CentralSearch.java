package uk.co.probablyfine.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CentralSearch {

	private final XPath xpath;
	
	private String url;
	
	public CentralSearch() {
		this.xpath = XPathFactory.newInstance().newXPath();
	}
	
	public Collection<String> getResult() throws MalformedURLException, SAXException, IOException, ParserConfigurationException, XPathExpressionException, URISyntaxException {
		
		final Collection<String> results = new ArrayList<String>();
		
		final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new URL(url).openStream());
		
		final NodeList nodes = (NodeList) xpath.evaluate("/response/result/doc", doc, XPathConstants.NODESET);
			
		for (int i = 0; i < nodes.getLength(); i++) {
			final String result = xpath.evaluate("str[@name='p']", nodes.item(i));
				
			if (!result.equals("pom"))
				results.add(getFormattedString(nodes.item(i)));
		}
		
		return results;
	}

	private String getFormattedString(final Node item) throws XPathExpressionException {

		final StringBuilder sb =  new StringBuilder()
				.append(xpath.evaluate("str[@name='g']", item))
				.append(" - ")
				.append(xpath.evaluate("str[@name='a']", item));
		
		if (!xpath.evaluate("str[@name='latestVersion']", item).equals(""))
			sb.append(" - ").append(xpath.evaluate("str[@name='latestVersion']", item));
		else if (!xpath.evaluate("str[@name='v']", item).equals(""))
			sb.append(" - ").append(xpath.evaluate("str[@name='v']", item));
		
		return sb.toString();
	}
	
	public CentralSearch searchGeneral(String query) throws UnsupportedEncodingException, URISyntaxException {
		
		this.url = new URI("http",
				"search.maven.org",
				"/solrsearch/select",
				"q="+URLEncoder.encode(query,"UTF-8")+"&wt=xml",
				null).toString();
		
		return this;
	}
	
	public CentralSearch searchByClassName(String query) throws UnsupportedEncodingException, URISyntaxException {
		
		this.url = new URI("http",
				"search.maven.org",
				"/solrsearch/select",
				"q=c:"+URLEncoder.encode(query,"UTF-8")+"&wt=xml",
				null).toString();
		
		return this;
	}
	
}
