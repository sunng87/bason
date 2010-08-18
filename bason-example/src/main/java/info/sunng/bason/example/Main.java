/**
 * 
 */
package info.sunng.bason.example;

import org.bson.BSONObject;

import info.sunng.bason.BasonManager;

/**
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
public class Main {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Passenger p = new Passenger();
		p.setName("Ninmm");
		p.setPackageWeight(27.2);
		p.setTicketId(3945);
		BSONObject bs = BasonManager.toBson(p);
		System.out.println(bs.toString());
	}

}
