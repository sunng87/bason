/**
 * 
 */
package info.sunng.bason.internal;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author SunNing
 *
 * @since Aug 25, 2010
 */
public class NameTypeTupleTest {

	/**
	 * Test method for {@link info.sunng.bason.internal.FieldInfo#getType()}.
	 */
	@Test
	public void testGetType() {
		
		FieldInfo t = new FieldInfo("hello", "int");
		
		assertEquals("java.lang.Integer", t.getType());
	}

}
