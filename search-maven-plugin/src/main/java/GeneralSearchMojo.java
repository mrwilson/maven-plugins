import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import uk.co.probablyfine.util.AbstractSearchMojo;

/**
 * General search
 * @goal search
 * @requiresProject false
 */
public class GeneralSearchMojo extends AbstractSearchMojo {

	@Override
	public void setParameters() {
		try {
			this.search = search.searchGeneral(query);
		} catch (UnsupportedEncodingException e) {
			getLog().error("Unsupported encoding.");
			getLog().error(e);
		} catch (URISyntaxException e) {
			getLog().error("Badly formed query url");
			getLog().error(e);
		}
		
	}

	

	
	
}
