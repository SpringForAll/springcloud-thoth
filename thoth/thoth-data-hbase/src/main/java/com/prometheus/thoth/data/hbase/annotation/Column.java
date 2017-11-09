package com.prometheus.thoth.data.hbase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String family() default "";

    String qualifier() default "";
}