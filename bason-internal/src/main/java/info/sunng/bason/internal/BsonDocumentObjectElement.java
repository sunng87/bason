/**
 * 
 */
package info.sunng.bason.internal;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

/**
 * A wrapper object represents a element, provides straight forward interfaces
 * for downstream use.
 * 
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
public class BsonDocumentObjectElement {
	
	private String className ;
	
	private List<String> fields = new ArrayList<String>();

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	/**
	 * construct a object element with a java model 
	 * @param ele
	 */
	public BsonDocumentObjectElement(Element ele){
		setClassName(ele.toString());
		
		List<? extends Element> enclosedElements = ele.getEnclosedElements();
		for (Element e: enclosedElements){
			String fieldName = e.accept(new GetterElementVisitor6(), null);
			if (fieldName != null){
				fields.add(fieldName);
			}
		}
	}

	public BsonDocumentObjectElement() {
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the fields
	 */
	public List<String> getFields() {
		return fields;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	
	
}
