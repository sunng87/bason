Bason
=====

Bason is a Java toolkit to generate [BSON](http://bsonspec.org "bson web site") serializer automatically.
The mapper class configured in bason.properties is generated at compile time which has no nagetive impact on performance
 (comparing with traditional reflection way).
 
Usage
-----
Annotate your Java bean with @BsonDocument to declare that this bean can be serialized
to or deserialized from bson.

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

run `maven compile` to generate Bason manager class you defined in *bason.properties*.

See the example project for detail.

Bason snapshot builds are available at [Sonatype OSS repository](https://oss.sonatype.org/content/repositories/snapshots/ "copy the link to repository section of your pom"). Put this segment in you pom.xml:

	<repositories>
		<repository>
			<id>sonatype oss snapshots</id>
			<snapshots>
				<updatePolicy>always</updatePolicy>
			</snapshots>
			<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
		</repository>
	</repositories>

	<dependencies>
		<dependency>
			<groupId>info.sunng.bason</groupId>
			<artifactId>bason-annotation</artifactId>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>info.sunng.bason</groupId>
			<artifactId>bason-internal</artifactId>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.mongodb</groupId>
			<artifactId>mongo-java-driver</artifactId>
		</dependency>
	</dependencies>

Currently, you have to depend on whole mongo-java-driver which is considered to be verbose. 
I reported [an issue](http://jira.mongodb.org/browse/JAVA-152 "mongodb jira") to mongodb team. It is said that the bson packages will be distributed separately since the version of 2.2.
