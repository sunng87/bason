/**
 * 
 */
package info.sunng.bason.example;

import info.sunng.bason.annotations.BsonDocument;

/**
 * @author SunNing
 *
 * @since Aug 20, 2010
 */
@BsonDocument
public class Flight {
	
	private String company;
	
	private String flightId;

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the flightId
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	

}
