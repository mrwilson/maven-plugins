validate-maven-plugin
=====================

A maven plugin to enforce project characteristics.

    mvn install # to install

##Use
 * mvn uk.co.probablyfine:validate-maven-plugin:\<goal\>
 * mvn validate:<goal> (if uk.co.probablyfine is in settings.xml as below)

        <pluginGroups> 
                <pluginGroup>uk.co.probablyfine</pluginGroup>
        </pluginGroups>

##Goals
 * validate:untracked-files - fail build if there are untracked files
 * validate:diff-upstream   - fail build if local commits are ahead of upstream
