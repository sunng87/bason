/**
 * 
 */
package info.sunng.bason.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>This annotation should be annotated on the getter of a java bean. It marks
 * the property as transient which will not be serialized to bson document.</p>
 * 
 * @author SunNing
 *
 * @since Aug 19, 2010
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BsonIgnore {

}
