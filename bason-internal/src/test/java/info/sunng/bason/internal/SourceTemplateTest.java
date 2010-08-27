/**
 * 
 */
package info.sunng.bason.internal;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author SunNing
 * 
 * @since Aug 25, 2010
 */
public class SourceTemplateTest {

	public List<BsonDocumentObjectElement> getBSOE() {
		List<BsonDocumentObjectElement> bsoes = new ArrayList<BsonDocumentObjectElement>();

		BsonDocumentObjectElement bsoe = new BsonDocumentObjectElement();
		bsoe.setClassName("info.sunng.bason.internal.sample.Address");

		List<FieldInfo> ntts = new ArrayList<FieldInfo>();
		ntts.add(new FieldInfo("street", "java.lang.String"));
		ntts.add(new FieldInfo("no", "int"));
		FieldInfo f = new FieldInfo("data", "java.util.Collection");
		f.setCollection(true);
		ntts.add(f);
		
		FieldInfo f2 = new FieldInfo("meta", "double[]");
		f2.setArray(true);
		ntts.add(f2);

		bsoe.setFields(ntts);

		bsoes.add(bsoe);

		return bsoes;
	}

	@Test()
	public void testSourceTemplate() throws Exception {
		List<BsonDocumentObjectElement> bsoes = getBSOE();

		Writer w = new PrintWriter(System.out);
		SourceTemplate st = new SourceTemplate(w,
				"info.sunng.bason.BasonManager", bsoes);
		st.writeSource();
	}

}
