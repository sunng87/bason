/**
 * 
 */
package info.sunng.bason.internal.visitors;

import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;

/**
 * @author SunNing
 *
 * @since Aug 26, 2010
 */
public class IsArrayVisitor extends SimpleTypeVisitor6<Boolean, Void> {

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleTypeVisitor6#defaultAction(javax.lang.model.type.TypeMirror, java.lang.Object)
	 */
	@Override
	protected Boolean defaultAction(TypeMirror e, Void p) {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleTypeVisitor6#visitArray(javax.lang.model.type.ArrayType, java.lang.Object)
	 */
	@Override
	public Boolean visitArray(ArrayType t, Void p) {
		return true;
	}

}
