/**
 * 
 */
package info.sunng.bason.internal.sample;

import org.bson.BSON;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;

/**
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
public final class BsonManager {
	
	public static final byte[] toBson(Object o){
		BSONObject bson = new BasicBSONObject();
		
		bson.put(null, null);
		
		return BSON.encode(bson);
	}
	
	public static final Address fromBson(Address o, BSONObject bson){
		if (bson.get("number") != null){
			o.setNumber((Integer)bson.get("number"));
		}
		return o;
	}

}
