A maven plugin that implements tinyjar ( https://blog.goeswhere.com/2012/02/tinyjar/ ) as a custom packaging option.

## Usage

Download and install (with mvn install) the tinyjar repository at https://github.com/FauxFaux/tinyjar

Install this plugin (with mvn install)

In the pom.xml, use 

      <packaging>tinyjar</packaging>

And under <build><plugins>...

      <plugin>
        <groupId>uk.co.probablyfine</groupId>
        <artifactId>tinyjar-maven-plugin</artifactId>
        <version>0.0.1</version>
        <extensions>true</extensions>
      </plugin>

The default execution of the packaging is immediately after the standard maven jar plugin.
