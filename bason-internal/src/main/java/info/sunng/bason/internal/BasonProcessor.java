/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.annotations.BsonDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic.Kind;

/**
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
@SupportedAnnotationTypes("info.sunng.bason.annotations.BsonDocument")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class BasonProcessor extends AbstractProcessor {

	public static final String DEFAULT_CONFIGURATION_FILE_NAME = "/bason.properties";

	private Properties properties = new Properties();

	public BasonProcessor() {

		try {
			readConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readConfig() throws Exception {
		InputStream in = this.getClass().getResourceAsStream(
				DEFAULT_CONFIGURATION_FILE_NAME);
		properties.load(in);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Messager messager = processingEnv.getMessager();

		messager.printMessage(Kind.NOTE, "Begin to process");
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(BsonDocument.class);

		if (elements.size() > 0) {
			List<BsonDocumentObjectElement> annotatedElements = new ArrayList<BsonDocumentObjectElement>();

			for (Element ele : elements) {
				BsonDocumentObjectElement bdoe = new BsonDocumentObjectElement(
						ele);
				annotatedElements.add(bdoe);
			}
			try {
				generateManagerClass(processingEnv.getFiler(),
						annotatedElements);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * 
	 * @return
	 */
	protected String getManagerClassName() {
		return properties.getProperty("bason.managerClassName");
	}

	/**
	 * 
	 * @param annotatedElements
	 * @throws IOException
	 */
	protected void generateManagerClass(Filer sourceFiler,
			List<BsonDocumentObjectElement> annotatedElements)
			throws IOException {
		String className = getManagerClassName();

		if (className == null) {
			throw new IllegalStateException("manager class name not set");
		}

		writeSource(sourceFiler, annotatedElements, className);

	}

	/**
	 * 
	 * @param writer
	 * @param annotatedElements
	 * @param className
	 * @throws IOException
	 */
	protected void writeSource(Filer filer,
			List<BsonDocumentObjectElement> annotatedElements, String className)
			throws IOException {
		JavaFileObject sourceFile = filer.createSourceFile(className);
		Writer writer = sourceFile.openWriter();

		SourceTemplate template = new SourceTemplate(writer, className,
				annotatedElements);
		template.writeSource();
	}

}
