/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.annotations.BsonDocument;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
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
import javax.tools.Diagnostic.Kind;

/**
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
@SupportedAnnotationTypes("info.sunng.bason.annotations.BsonDocument")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class BasonProcessor extends AbstractProcessor {

	public BasonProcessor() {
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
	 * @param annotatedElements
	 * @throws IOException
	 */
	protected void generateManagerClass(Filer sourceFiler,
			List<BsonDocumentObjectElement> annotatedElements)
			throws IOException {
		String className = "info.sunng.bason.BasonManager";

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
		
		SourceTemplate template = new SourceTemplate(filer, annotatedElements);
		template.writeSource(className);
	}

}
