package uk.co.probablyfine.util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public abstract class AbstractSearchMojo extends AbstractMojo {

	/**
     * @parameter expression="${query}" default-value=" "
     */
	protected String query;
	
	protected CentralSearch search = new CentralSearch();
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		getLog().info("Attempting search...");
		
		this.setParameters();
		
		if (this.query.equals(" ")) 
			getLog().info("No query given, specify query with -Dquery=<querystring>");
		else 
			prettyPrint(executeQuery());
		
	}

	private void prettyPrint(final Collection<String> results) {
		
		if (results.isEmpty())
			getLog().info("No results found for \""+query+"\"");
		else {
			getLog().info("Results for \""+query+"\":");
		
			for (final String string : results) {
				getLog().info("\t"+string);
			}
		}
	}

	public abstract void setParameters();
	
	public Collection<String> executeQuery(){
		try {
			return this.search.getResult();
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
