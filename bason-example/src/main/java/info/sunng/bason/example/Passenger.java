/**
 * 
 */
package info.sunng.bason.example;

import info.sunng.bason.annotations.BsonDocument;

/**
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
@BsonDocument
public class Passenger {
	
	private double packageWeight;
	
	private long ticketId;
	
	private String name;

	/**
	 * @return the packageWeight
	 */
	public double getPackageWeight() {
		return packageWeight;
	}

	/**
	 * @param packageWeight the packageWeight to set
	 */
	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}

	/**
	 * @return the ticketId
	 */
	public long getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
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

}
