/**
 * 
 */
package info.sunng.bason.example;


import java.util.Date;

import info.sunng.bason.annotations.BsonAlias;
import info.sunng.bason.annotations.BsonDocument;
import info.sunng.bason.annotations.BsonIgnore;

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
	
	private Date createdDate;

	/**
	 * @return the packageWeight
	 */
	@BsonIgnore
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
	@BsonAlias("ticket")
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

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

}
