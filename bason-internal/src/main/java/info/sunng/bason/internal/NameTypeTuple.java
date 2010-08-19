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
public class NameTypeTuple {
	
	private String name;
	
	private String type;
	
	public NameTypeTuple(String name, String type){
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
			return "java.lang."+StringUtils.capticalize(type);
		}
		
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
