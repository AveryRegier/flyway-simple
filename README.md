# flyway-simple
A simple command line for flyway that integrates well with maven.

At the command line:
  mvn install

Then add this to your POM:

    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>exec-maven-plugin</artifactId>
      <version>1.3.2</version>
      <executions>
          <execution>
              <phase>generate-sources</phase>
              <goals>
                  <goal>java</goal>
              </goals>
          </execution>
      </executions>
      <configuration>
          <includeProjectDependencies>true</includeProjectDependencies>
          <includePluginDependencies>true</includePluginDependencies>
          <mainClass>com.github.averyregier.flyway.simple.Main</mainClass>
          <workingDirectory>${project.basedir}/src/resources</workingDirectory>
          <arguments>
              <argument>migrate</argument>
              <argument>-url=${flyway.url}</argument>
              <argument>-user=${flyway.user}</argument>
              <argument>-password=${flyway.password}</argument>
              <!--<argument>-schemas=${flyway.schemas}</argument> for some reason schemas fail right now-->
              <argument>-debug</argument>
              <argument>-locations=filesystem:${project.basedir}/src/main/resources/db/migration</argument>
          </arguments>
      </configuration>
      <dependencies>
          <dependency>
              <groupId>com.github.averyregier.flyway</groupId>
              <artifactId>flyway-simple</artifactId>
              <version>1.0-SNAPSHOT</version>
          </dependency>
      </dependencies>
  </plugin>
