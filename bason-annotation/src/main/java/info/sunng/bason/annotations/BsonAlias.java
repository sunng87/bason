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
 * <p>This annotation allows to specify a custom name of property in the bson
 * document. It should be annotated on the getter.</p>
 * 
 * @author SunNing
 *
 * @since Aug 19, 2010
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BsonAlias {
	String value();
}
