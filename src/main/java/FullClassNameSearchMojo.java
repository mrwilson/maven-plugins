import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import uk.co.probablyfine.util.AbstractSearchMojo;

/**
 * Search via full class name
 * @goal fullclass
 * @requiresProject false
 */
public class FullClassNameSearchMojo extends AbstractSearchMojo {

	@Override
	public void setParameters() {
		try {
			this.search = search.searchByFullClassName(query);
		} catch (UnsupportedEncodingException e) {
			getLog().error("Unsupported encoding.");
			getLog().error(e);
		} catch (URISyntaxException e) {
			getLog().error("Badly formed query url");
			getLog().error(e);
		}
		
	}


}
