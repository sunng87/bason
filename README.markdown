Bason
=====

Bason is a Java toolkit to generate [BSON](http://bsonspec.org "bson web site") serializer automatically.
The mapper class *info.sunng.bason.BasonManager* is generated at compile time which has no nagetive impact on performance
 (comparing with tranditional reflection way).
 
Usage
-----
Annotate your Java bean with @BsonDocument to declare that this bean can be serialize
to or deserialize from bson.

@BsonDocument
public class Passenger {
	
	private double packageWeight;
	
	public double getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}

}


For maven user, then add such segment on certain part of your pom.xml

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

if you are not maven user, add corresponding javac directive when compiling.

run `maven compile` to generate *BasonManager*.
