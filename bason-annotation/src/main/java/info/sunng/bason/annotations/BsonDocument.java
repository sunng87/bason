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
 * <p>Marks a java bean to be processed by the bason processor, then serialization
 * and deserialization code will be generated into the manager class.</p>
 * <p>This annotation should be annotated on a standard java bean with getter
 * and setter for each property. </p>
 * 
 * @author SunNing
 *
 * @since Aug 18, 2010
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BsonDocument {

}
