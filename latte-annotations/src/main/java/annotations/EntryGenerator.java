package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名 FestEC2 on 2018/5/24.
 * 包名   annotations
 * 创建者   82354
 * 创建时间   2018/5/24 18:42
 * 描述  TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemplete();
}
