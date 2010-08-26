/**
 * 
 */
package info.sunng.bason.internal.visitors;

import java.util.Collection;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;

/**
 * @author SunNing
 *
 * @since Aug 26, 2010
 */
public class IsCollectionVisitor extends SimpleTypeVisitor6<Boolean, Void> {

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleTypeVisitor6#defaultAction(javax.lang.model.type.TypeMirror, java.lang.Object)
	 */
	@Override
	protected Boolean defaultAction(TypeMirror e, Void p) {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleTypeVisitor6#visitDeclared(javax.lang.model.type.DeclaredType, java.lang.Object)
	 */
	@Override
	public Boolean visitDeclared(DeclaredType t, Void p) {
		String className = t.toString();
		try {
			Class<?> c= Class.forName(className);
			return c.isAssignableFrom(Collection.class);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		return false;
	}

}
