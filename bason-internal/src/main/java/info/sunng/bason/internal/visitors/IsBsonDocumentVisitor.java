/**
 * 
 */
package info.sunng.bason.internal.visitors;

import info.sunng.bason.annotations.BsonDocument;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor6;

class IsBsonDocumentVisitor extends SimpleTypeVisitor6<Boolean	, Void> {
	
	/* (non-Javadoc)
	 * @see javax.lang.model.util.SimpleTypeVisitor6#visitDeclared(javax.lang.model.type.DeclaredType, java.lang.Object)
	 */
	@Override
	public Boolean visitDeclared(DeclaredType t, Void p) {
		return null != t.asElement().getAnnotation(BsonDocument.class);
	}
	
	@Override
	protected Boolean defaultAction(TypeMirror e, Void p){
		return false;
	}
	
}