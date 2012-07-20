Maven Search plugin

Use
 * mvn uk.co.probablyfine:maven-search-plugin:<goal> -Dquery="<query>"
 * mvn search:<goal> (if uk.co.probablyfine is in settings.xml under pluginGroups)

  <pluginGroups> 
    <pluginGroup>uk.co.probablyfine</pluginGroup>
  </pluginGroups>

Goals
 * search:search - general search terms
 * search:artifact - search by artifactId
 * search:group - search by groupId
 * search:classname - search by name of class
 * search:fullclass - search by package + classname
	
