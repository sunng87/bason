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
public class GetterElementVisitor6 extends ElementKindVisitor6<NameTypeTuple, Void> {

	/* (non-Javadoc)
	 * @see javax.lang.model.util.ElementKindVisitor6#visitExecutableAsMethod(javax.lang.model.element.ExecutableElement, java.lang.Object)
	 */
	@Override
	public NameTypeTuple visitExecutableAsMethod(ExecutableElement e, Void p) {
		// annotated with @BsonIgnore
		if (e.getAnnotation(BsonIgnore.class) != null){
			return null;
		}
		String name = e.getSimpleName().toString();
		if(name.startsWith("get")) {
			StringBuffer propertyName = new StringBuffer(name.substring(3));
			char firstChar = propertyName.charAt(0);
			propertyName.setCharAt(0, Character.toLowerCase(firstChar));
			String fieldName = propertyName.toString();
			String typeName = e.getReturnType().toString();
			
			return new NameTypeTuple(fieldName, typeName);
		} else {
			return null;
		}
	}

}
