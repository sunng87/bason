/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.annotations.BsonAlias;
import info.sunng.bason.annotations.BsonDocument;
import info.sunng.bason.annotations.BsonIgnore;
import info.sunng.bason.internal.GetterElementVisitor6.IsBsonDocumentVisitor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.util.ElementKindVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;

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
	private NameTypeTuple createNameTypeTuple(ExecutableElement ele){
		String name = ele.getSimpleName().toString();
		StringBuffer propertyName = new StringBuffer(name.substring(3));
		char firstChar = propertyName.charAt(0);
		propertyName.setCharAt(0, Character.toLowerCase(firstChar));
		String fieldName = propertyName.toString();
		String typeName = ele.getReturnType().toString();
		
		NameTypeTuple ntt = new NameTypeTuple(fieldName, typeName);
		
		if (ele.getReturnType().accept(new IsBsonDocumentVisitor(), null)){
			ntt.setDocument(true);
		}
		
		BsonAlias alias = ele.getAnnotation(BsonAlias.class);
		if (alias != null){
			ntt.setAlias(alias.value());
		}
		
		return ntt;
	}
	
	
	static class IsBsonDocumentVisitor extends SimpleTypeVisitor6<Boolean	, Void> {
		
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

}
