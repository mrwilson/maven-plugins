import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import uk.co.probablyfine.util.AbstractSearchMojo;

/**
 * Search via classname
 * @goal classname
 * @requiresProject false
 */
public class ClassNameSearchMojo extends AbstractSearchMojo {

	@Override
	public void setParameters() {
		try {
			this.search = search.searchByClassName(query);
		} catch (UnsupportedEncodingException e) {
			getLog().error("Unsupported encoding.");
			getLog().error(e);
		} catch (URISyntaxException e) {
			getLog().error("Badly formed query url");
			getLog().error(e);
		}
		
	}


}
