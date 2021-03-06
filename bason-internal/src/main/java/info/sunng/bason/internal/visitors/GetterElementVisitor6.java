/**
 * 
 */
package info.sunng.bason.internal.visitors;

import info.sunng.bason.annotations.BsonAlias;
import info.sunng.bason.annotations.BsonIgnore;
import info.sunng.bason.internal.FieldInfo;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementKindVisitor6;

/**
 * @author SunNing
 * 
 * @since Aug 18, 2010
 */
public class GetterElementVisitor6 extends ElementKindVisitor6<FieldInfo, Void> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.lang.model.util.ElementKindVisitor6#visitExecutableAsMethod(javax
	 * .lang.model.element.ExecutableElement, java.lang.Object)
	 */
	@Override
	public FieldInfo visitExecutableAsMethod(ExecutableElement e, Void p) {
		// annotated with @BsonIgnore
		if (e.getAnnotation(BsonIgnore.class) != null) {
			return null;
		}
		String name = e.getSimpleName().toString();
		if (name.startsWith("get")) {

			return createNameTypeTuple(e);

		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param ele
	 * @return
	 */
	private FieldInfo createNameTypeTuple(ExecutableElement ele) {
		String name = ele.getSimpleName().toString();
		StringBuffer propertyName = new StringBuffer(name.substring(3));
		char firstChar = propertyName.charAt(0);
		propertyName.setCharAt(0, Character.toLowerCase(firstChar));
		String fieldName = propertyName.toString();
		String typeName = ele.getReturnType().toString();

		FieldInfo ntt = new FieldInfo(fieldName, typeName);

		if (ele.getReturnType().accept(new IsBsonDocumentVisitor(), null)) {
			ntt.setDocument(true);
		}

		if (ele.getReturnType().accept(new IsArrayVisitor(), null)) {
			ntt.setArray(true);

			if (typeName.indexOf('.') == -1) {
				throw new IllegalStateException(
						"Primitive array not supported currently, use wrapped ones");
			}

		} else if (ele.getReturnType().accept(new IsCollectionVisitor(), null)) {
			ntt.setCollection(true);
		}

		BsonAlias alias = ele.getAnnotation(BsonAlias.class);
		if (alias != null) {
			ntt.setAlias(alias.value());
		}

		return ntt;
	}

}
