import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

import uk.co.probablyfine.util.CentralSearch;
import uk.co.probablyfine.util.AbstractSearchMojo;

/**
 * Search via classname
 * @goal classname
 * @requiresProject false
 */
public class ClassNameSearchMojo extends AbstractSearchMojo {

	@Override
	public Collection<String> executeQuery() {
		try {
			return new CentralSearch().searchByClassName(this.query).getResult();
		} catch (MalformedURLException e) {
			getLog().error("Badly formed url, perhaps due to bad query?");
			getLog().error(e);
		} catch (SAXException e) {
			getLog().error("Error parsing XML.");
			getLog().error(e);
		} catch (IOException e) {
			getLog().error("Error reading from site.");
			getLog().error(e);
		} catch (ParserConfigurationException e) {
			getLog().error("Badly configured xml parser.");
			getLog().error(e);
		} catch (XPathExpressionException e) {
			getLog().error("Badly formed XPath expression");
			getLog().error(e);
		} catch (URISyntaxException e) {
			getLog().error("Bad url syntax");
			getLog().error(e);
		} 
		
		return Lists.newArrayList();

	}
}
