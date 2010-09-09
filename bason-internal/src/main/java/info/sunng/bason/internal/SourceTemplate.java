/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.utils.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This is a thread unsafe implementation
 * 
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
public class SourceTemplate {

	private Writer writer;

	private List<BsonDocumentObjectElement> annotatedElements;

	private String className;

	public SourceTemplate(Writer writer, String className,
			List<BsonDocumentObjectElement> annotatedElements) {
		this.writer = writer;
		this.className = className;
		this.annotatedElements = annotatedElements;
	}

	public void writeSource() throws IOException {
		doWriteHead(className);
		doWriteBody(annotatedElements);
		doWriteTail();

	}

	protected void doWriteHead(String className) throws IOException {
		writer.write(StringUtils.asLine(0, "package ", StringUtils.splitAtLast(
				className, ".")[0], ";"));
		// import org.bson.*
		writer.write(StringUtils.asLine(0, "import org.bson.*;"));
		writer.write(StringUtils.asLine(0, "import org.bson.types.*;"));
		// import java.util.*
		writer.write(StringUtils.asLine(0, "import java.util.*;"));
		writer.write(StringUtils
				.asLine(0, "import javax.annotation.Generated;"));
		writer.write(StringUtils.asLine(0, "@SuppressWarnings(", StringUtils
				.quote("unchecked"), ")"));

		// @Generated({...})
		writer
				.write(StringUtils.asLine(0, "@Generated(", "value=",
						StringUtils.quote(BasonProcessor.class.getName()), ",",
						"date=", StringUtils.quote(new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss.SSSZ")
								.format(new Date())), ")"));

		// public final class ... {
		writer.write(StringUtils.asLine(0, "public final class ", StringUtils
				.splitAtLast(className, ".")[1], "{"));
	}

	/**
	 * 
	 * @param writer
	 * @param elements
	 * @throws IOException
	 */
	protected void doWriteBody(List<BsonDocumentObjectElement> elements)
			throws IOException {

		for (BsonDocumentObjectElement ele : annotatedElements) {
			writeGetter(ele);

			writeSetter(ele);
		}

	}

	/**
	 * 
	 * @param writer
	 * @param ele
	 * @throws IOException
	 */
	private void writeSetter(BsonDocumentObjectElement ele) throws IOException {
		writer.write(StringUtils.asLine(4, "public static final ", ele
				.getClassName(), " fromBson(", ele.getClassName(),
				" o, BSONObject bson){"));

		writer.write(StringUtils.asLine(8, "if (o == null || bson == null) {"));
		writer.write(StringUtils
				.asLine(12, "throw new NullPointerException();"));
		writer.write(StringUtils.asLine(8, "}"));

		for (FieldInfo field : ele.getFields()) {
			String bsonAttrName = field.getAlias() == null ? field.getName()
					: field.getAlias();

			writer.write(StringUtils.asLine(8, "if (bson.get(", StringUtils
					.quote(bsonAttrName), ") != null){"));
			if (field.isDocument()) {
				writer.write(StringUtils.asLine(8, "o.set", StringUtils
						.capticalize(field.getName()), "(fromBson(new ", field
						.getType(), "(), (BSONObject)bson.get(", StringUtils
						.quote(bsonAttrName), ")));"));
			} else if (field.isArray()) /* array setter */{
				writeArraySetter(field, bsonAttrName);
			} else if (field.isCollection()) /* collection setter */{
				writeCollectionSetter(field, bsonAttrName);
			} else {
				writer.write(StringUtils.asLine(12, "o.set", StringUtils
						.capticalize(field.getName()), "((", field.getType(),
						")bson.get(", StringUtils.quote(bsonAttrName), "));"));
			}
			writer.write(StringUtils.asLine(8, "}"));
		}

		writer.write(StringUtils.asLine(8, "return o;"));
		writer.write(StringUtils.asLine(4, "}"));
	}

	/**
	 * 
	 * construct a collection from BSONList
	 * 
	 * @param writer2
	 * @param field
	 */
	protected void writeCollectionSetter(FieldInfo field, String bsonAttrName)
			throws IOException {
		if (field.getType().startsWith("java.util.Collection")) {
			writer.write(StringUtils.asLine(12, "o.set", StringUtils
					.capticalize(field.getName()),
					"(new ArrayList((BasicBSONList)bson.get(", StringUtils
							.quote(bsonAttrName), ")));"));
		} else if (field.getType().startsWith("java.util.Set")) {
			writer.write(StringUtils.asLine(12, "o.set", StringUtils
					.capticalize(field.getName()),
					"(new HashSet((BasicBSONList)bson.get(", StringUtils
							.quote(bsonAttrName), ")));"));
		} else if (field.getType().startsWith("java.util.List")) {
			writer.write(StringUtils.asLine(12, "o.set", StringUtils
					.capticalize(field.getName()),
					"(new ArrayList((BasicBSONList)bson.get(", StringUtils
							.quote(bsonAttrName), ")));"));
		} else if (field.getType().startsWith("java.util.SortedSet")) {
			writer.write(StringUtils.asLine(12, "o.set", StringUtils
					.capticalize(field.getName()),
					"(new TreeSet((BasicBSONList)bson.get(", StringUtils
							.quote(bsonAttrName), ")));"));
		}
	}

	/**
	 * 
	 * construct an array from BSONList
	 * 
	 * @param writer2
	 * @param field
	 */
	protected void writeArraySetter(FieldInfo field, String bsonAttrName)
			throws IOException {
		writer.write(StringUtils.asLine(12, "o.set", StringUtils
				.capticalize(field.getName()), "(((BasicBSONList)bson.get(",
				StringUtils.quote(bsonAttrName), ")).toArray(new ", StringUtils
						.arrayTypeToZeroLengthArray(field.getType()), "));"));
	}

	/**
	 * 
	 * @param writer
	 * @param ele
	 * @throws IOException
	 */
	private void writeGetter(BsonDocumentObjectElement ele) throws IOException {
		writer.write(StringUtils.asLine(4,
				"public static final BSONObject toBson(", ele.getClassName(),
				" o){"));

		writer.write(StringUtils.asLine(8, "if (o == null) {"));
		writer.write(StringUtils
				.asLine(12, "throw new NullPointerException();"));
		writer.write(StringUtils.asLine(8, "}"));

		writer.write(StringUtils.asLine(8,
				"BSONObject bson = new BasicBSONObject();"));

		for (FieldInfo field : ele.getFields()) {
			String bsonAttrName = field.getAlias() == null ? field.getName()
					: field.getAlias();

			// this field is another bson document type
			if (field.isDocument()) {
				// just write converted bson document to this field
				writer.write(StringUtils.asLine(8, "bson.put(", StringUtils
						.quote(bsonAttrName), ",", "toBson(", "o.get",
						StringUtils.capticalize(field.getName()), "()", "));"));
			} else if (field.isCollection()) /* collection getter */{
				writeCollectionGetter(field, bsonAttrName);
			} else if (field.isArray()) /* array getter */{
				writeArrayGetter(field, bsonAttrName);
			} else {
				writer.write(StringUtils.asLine(8, "bson.put(", StringUtils
						.quote(bsonAttrName), ",", "o.get", StringUtils
						.capticalize(field.getName()), "());"));
			}
		}

		writer.write(StringUtils.asLine(8, "return bson;"));

		writer.write(StringUtils.asLine(4, "}"));
	}

	/**
	 * construct a BSONList from an array
	 * 
	 * @param writer2
	 * @param field
	 */
	protected void writeArrayGetter(FieldInfo field, String bsonAttrName)
			throws IOException {
		writer.write(StringUtils.asLine(8, "if (o.get", StringUtils
				.capticalize(field.getName()), "()!= null) {"));

		writer.write(StringUtils.asLine(12, "bson.put(", StringUtils
				.quote(bsonAttrName), ",", "Arrays.asList(o.get", StringUtils
				.capticalize(field.getName()), "()));"));

		writer.write(StringUtils.asLine(8, "}"));
	}

	/**
	 * construct a BSONList from a collection
	 * 
	 * @param writer2
	 * @param field
	 */
	protected void writeCollectionGetter(FieldInfo field, String bsonAttrName)
			throws IOException {
		writer.write(StringUtils.asLine(8, "if (o.get", StringUtils
				.capticalize(field.getName()), "()!= null) {"));
		writer.write(StringUtils.asLine(12, "bson.put(", StringUtils
				.quote(bsonAttrName), ",", "new ArrayList(o.get", StringUtils
				.capticalize(field.getName()), "()));"));
		writer.write(StringUtils.asLine(8, "}"));
	}

	/**
	 * 
	 * @param writer
	 * @throws IOException
	 */
	protected void doWriteTail() throws IOException {

		writer.write(StringUtils.asLine(0, "}"));

	}

}
