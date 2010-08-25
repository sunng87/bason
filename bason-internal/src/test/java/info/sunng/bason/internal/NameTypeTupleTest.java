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
	 * Test method for {@link info.sunng.bason.internal.NameTypeTuple#getType()}.
	 */
	@Test
	public void testGetType() {
		
		NameTypeTuple t = new NameTypeTuple("hello", "int");
		
		assertEquals("java.lang.Integer", t.getType());
	}

}
