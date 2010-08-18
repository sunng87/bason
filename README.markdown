Bason
=====

Bason is a Java toolkit to generate [BSON][http://bsonspec.org] serializer automatically.
The mapper class *info.sunng.bason.BasonManager* is generated at compile time which has no nagetive impact on performance
 (comparing with tranditional reflection way).
 
Usage
-----
For maven user, add such segment on certain part of your pom.xml

<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>

	<configuration>
		<source>1.6</source>
		<target>1.6</target>

		<compilerArguments>
			<processor>info.sunng.bason.internal.BasonProcessor</processor>
		</compilerArguments>
	</configuration>
</plugin>

run `maven compile` to generate *BasonManager*.