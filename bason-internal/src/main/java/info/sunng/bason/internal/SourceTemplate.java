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
		doWriteHead(writer, className);
		doWriteBody(writer, annotatedElements);
		doWriteTail(writer);

		writer.flush();

		writer.close();
	}

	protected void doWriteHead(Writer writer, String className)
			throws IOException {
		writer.write(StringUtils.asLine(0, "package ", StringUtils.splitAtLast(
				className, ".")[0], ";"));
		// import org.bson.*
		writer.write(StringUtils.asLine(0, "import org.bson.*;"));
		writer.write(StringUtils
				.asLine(0, "import javax.annotation.Generated;"));

		// @Generated({...})
		writer.write(StringUtils
				.asLine(0, "@Generated(", "value=", StringUtils
						.quote(BasonProcessor.class.getName()), ",", "date=",
						StringUtils.quote(new SimpleDateFormat(
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
	protected void doWriteBody(Writer writer,
			List<BsonDocumentObjectElement> elements) throws IOException {

		for (BsonDocumentObjectElement ele : annotatedElements) {
			writeGetter(writer, ele);

			writeSetter(writer, ele);
		}

	}

	/**
	 * 
	 * @param writer
	 * @param ele
	 * @throws IOException
	 */
	private void writeSetter(Writer writer, BsonDocumentObjectElement ele)
			throws IOException {
		writer.write(StringUtils.asLine(4, "public static final ", ele
				.getClassName(), " fromBson(", ele.getClassName(),
				" o, BSONObject bson){"));

		writer.write(StringUtils.asLine(8, "if (o == null || bson == null) {"));
		writer.write(StringUtils
				.asLine(12, "throw new NullPointerException();"));
		writer.write(StringUtils.asLine(8, "}"));

		for (NameTypeTuple field : ele.getFields()) {
			String bsonAttrName = field.getAlias() == null ? field.getName()
					: field.getAlias();

			if (field.isDocument()) {
				writer.write(StringUtils.asLine(8, "o.set", StringUtils
						.capticalize(field.getName()), "(fromBson(new ", field
						.getType(), "(), (BSONObject)bson.get(", StringUtils
						.quote(bsonAttrName), ")));"));
			} else {
				writer.write(StringUtils.asLine(8, "if (bson.get(", StringUtils
						.quote(bsonAttrName), ") != null){"));
				writer.write(StringUtils.asLine(12, "o.set", StringUtils
						.capticalize(field.getName()), "((", field.getType(),
						")bson.get(", StringUtils.quote(bsonAttrName), "));"));
				writer.write(StringUtils.asLine(8, "}"));
			}
		}

		writer.write(StringUtils.asLine(8, "return o;"));
		writer.write(StringUtils.asLine(4, "}"));
	}

	/**
	 * 
	 * @param writer
	 * @param ele
	 * @throws IOException
	 */
	private void writeGetter(Writer writer, BsonDocumentObjectElement ele)
			throws IOException {
		writer.write(StringUtils.asLine(4,
				"public static final BSONObject toBson(", ele.getClassName(),
				" o){"));

		writer.write(StringUtils.asLine(8, "if (o == null) {"));
		writer.write(StringUtils
				.asLine(12, "throw new NullPointerException();"));
		writer.write(StringUtils.asLine(8, "}"));

		writer.write(StringUtils.asLine(8,
				"BSONObject bson = new BasicBSONObject();"));

		for (NameTypeTuple field : ele.getFields()) {
			String bsonAttrName = field.getAlias() == null ? field.getName()
					: field.getAlias();

			// this field is another bson document type
			if (field.isDocument()) {
				// just write converted bson document to this field
				writer.write(StringUtils.asLine(8, "bson.put(", StringUtils
						.quote(bsonAttrName), ",", "toBson(", "o.get",
						StringUtils.capticalize(field.getName()), "()", "));"));
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
	 * 
	 * @param writer
	 * @throws IOException
	 */
	protected void doWriteTail(Writer writer) throws IOException {

		writer.write(StringUtils.asLine(0, "}"));

	}

}
