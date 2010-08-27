/**
 * 
 */
package info.sunng.bason.internal;

import info.sunng.bason.utils.StringUtils;

/**
 * @author SunNing
 *
 * @since Aug 19, 2010
 */
public class FieldInfo {
	
	private String name;
	
	private String type;
	
	private String alias;
	
	private boolean document;
	
	private boolean array;
	
	private boolean collection;
	
	public FieldInfo(String name, String type){
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * test if the type is a primitive (eg. int, boolean), then convert it to 
	 * 
	 * @return the type
	 */
	public String getType() {
		// is primitive type
		if (type.indexOf('.') == -1){
			
			if (type.equals("int")){
				return "java.lang.Integer";
			}
			return "java.lang."+StringUtils.capticalize(type);
		}
		
		return type;
	}
	
	public String getTypeRaw() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(boolean document) {
		this.document = document;
	}

	/**
	 * @return the document
	 */
	public boolean isDocument() {
		return document;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isArray() {
		return array;
	}

	/**
	 * @param array the array to set
	 */
	public void setArray(boolean array) {
		this.array = array;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	/**
	 * @return the collection
	 */
	public boolean isCollection() {
		return collection;
	}

}
