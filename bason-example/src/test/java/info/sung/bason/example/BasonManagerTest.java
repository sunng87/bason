/**
 * 
 */
package info.sung.bason.example;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import info.sunng.bason.BasonManager;
import info.sunng.bason.example.Flight;
import info.sunng.bason.example.Passenger;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.BasicBSONList;
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
		
		f.setCommands(new Integer[]{28,43,54,55,1});
		
		f.setDrivers(new HashSet<String>(Arrays.asList("Tom Hanks", "Nico Kiderman")));
		
		p.setFlight(f);
		
		BSONObject o =BasonManager.toBson(p);
		System.out.println(o);
		
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
		BasicBSONList drivers = new BasicBSONList();
		drivers.add("Tomas");
		drivers.add("Hanks");
		f.put("drivers", drivers);
		
		BasicBSONList commands = new BasicBSONList();
		commands.add(44);
		commands.add(43);
		commands.add(59);
		
		f.put("commands", commands);
		
//		f.put("capacity", 2);
		
		s.put("flight", f);
		
		System.out.println(s);
		Passenger p = BasonManager.fromBson(new Passenger(), s);
		
		assertEquals(2003l, p.getTicketId());
		assertEquals("CA", p.getFlight().getCompany());
		assertEquals(2, p.getFlight().getDrivers().size());
	}
	
	@Test
	public void testNullField() {
		BSONObject f = new BasicBSONObject();
		f.put("company", "CA");
		
		Flight f2 = BasonManager.fromBson(new Flight(), f);
		
		assertEquals(0, f2.getCapacity());
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull(){
		BasonManager.toBson((Flight)null);
	}

}
