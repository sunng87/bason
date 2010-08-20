/**
 * 
 */
package info.sung.bason.example;

import java.util.Date;

import info.sunng.bason.BasonManager;
import info.sunng.bason.example.Flight;
import info.sunng.bason.example.Passenger;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @author SunNing
 *
 * @since Aug 19, 2010
 */
public class BasonManagerTest {
	
	@Test
	public void testSerilization(){
		Passenger p = new Passenger();
		
		p.setCreatedDate(new Date());
		p.setName("Nimbus");
		p.setPackageWeight(200.3);
		p.setTicketId(28773l);
		
		Flight f = new Flight();
		f.setCompany("CA");
		f.setFlightId("CA-2002");
		
		p.setFlight(f);
		
		BSONObject o =BasonManager.toBson(p);
		
		assertEquals("Nimbus", o.get("name"));
		assertEquals(28773l, o.get("ticket"));
		assertEquals("CA", ((BSONObject)o.get("flight")).get("company"));
	}
	
	@Test
	public void testDeserilization(){
		BSONObject s= new BasicBSONObject();
		s.put("name", "tom");
		s.put("ticket", 2003l);
		
		BSONObject f = new BasicBSONObject();
		f.put("company", "CA");
		
		s.put("flight", f);
		
		System.out.println(s);
		Passenger p = BasonManager.fromBson(new Passenger(), s);
		
		assertEquals(2003l, p.getTicketId());
		assertEquals("CA", p.getFlight().getCompany());
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull(){
		BasonManager.toBson((Flight)null);
	}

}
