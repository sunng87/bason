/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.utils.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;

/**
 * This is a thread unsafe implementation
 * 
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
public class SourceTemplate {

	private Filer filer;

	private List<BsonDocumentObjectElement> annotatedElements;

	public SourceTemplate(Filer filer,
			List<BsonDocumentObjectElement> annotatedElements) {
		this.filer = filer;

		this.annotatedElements = annotatedElements;
	}

	public void writeSource(String className) throws IOException {
		JavaFileObject file = filer.createSourceFile(className);
		Writer writer = file.openWriter();

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
		writer.write(StringUtils.asLine(0,
				"import javax.annotation.Generated;"));

		// @Generated({...})
		writer
				.write(StringUtils.asLine(0, "@Generated({\"", className,
						"\"})"));

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
			System.out.println(ele.getClassName());
			writer.write(StringUtils.asLine(4,
					"public static final BSONObject toBson(", ele
							.getClassName(), " o){"));

			writer.write(StringUtils.asLine(8,
					"BSONObject bson = new BasicBSONObject();"));

			for (String field : ele.getFields()) {
				writer.write(StringUtils.asLine(8, "bson.put(", StringUtils
						.quote(field), ",", "o.get", StringUtils
						.capticalize(field), "());"));
			}

			writer.write(StringUtils.asLine(8, "return bson;"));

			writer.write(StringUtils.asLine(4, "}"));
		}

	}

	protected void doWriteTail(Writer writer) throws IOException {

		writer.write(StringUtils.asLine(0, "}"));

	}

}
