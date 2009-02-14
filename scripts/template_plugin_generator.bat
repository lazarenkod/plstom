rem %1 - path to plugin root folder
rem %2 - plugin name
cd %1
mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=com.pl.plugins -DartifactId=%2