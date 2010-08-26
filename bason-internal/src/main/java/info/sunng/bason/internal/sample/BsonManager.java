/**
 * 
 */
package info.sunng.bason.internal.sample;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.bson.BSON;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;

import com.mongodb.BasicDBList;

/**
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
public final class BsonManager {
	
	public static final byte[] toBson(Object o){
		BSONObject bson = new BasicBSONObject();
		
		bson.put(null, null);
		
		BasicDBList list = new BasicDBList();
		if (new String[]{"an"}.getClass().isArray()){
			
		} 
		if (new ArrayList<String>() instanceof Collection){
			list.addAll(new ArrayList());
		}
		bson.put("s", null);
		
		return BSON.encode(bson);
	}
	
	public static final Address fromBson(Address o, BSONObject bson){
		if (bson.get("number") != null){
			o.setNumber((Integer)bson.get("number"));
		}
		return o;
	}

}
