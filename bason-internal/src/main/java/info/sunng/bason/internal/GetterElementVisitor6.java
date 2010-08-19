/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.annotations.BsonIgnore;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementKindVisitor6;

/**
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
public class GetterElementVisitor6 extends ElementKindVisitor6<String, Void> {

	/* (non-Javadoc)
	 * @see javax.lang.model.util.ElementKindVisitor6#visitExecutableAsMethod(javax.lang.model.element.ExecutableElement, java.lang.Object)
	 */
	@Override
	public String visitExecutableAsMethod(ExecutableElement e, Void p) {
		// annotated with @BsonIgnore
		if (e.getAnnotation(BsonIgnore.class) != null){
			return null;
		}
		String name = e.getSimpleName().toString();
		if(name.startsWith("get")) {
			StringBuffer propertyName = new StringBuffer(name.substring(3));
			char firstChar = propertyName.charAt(0);
			propertyName.setCharAt(0, Character.toLowerCase(firstChar));
			return propertyName.toString();
		} else {
			return null;
		}
	}

}
