package com.prometheus.thoth.common.util;


import com.prometheus.thoth.common.exception.ArgumentException;
import com.prometheus.thoth.common.exception.ErrorCode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by liangliang on 2017/05/05.
 *
 * @author liangliang
 * @since 2017/05/05
 */
public class AssertUtils {

    /**
     * Assert that an object is {@code null}.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object    the object to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not {@code null}
     */
    public static void isNull(Object object, ErrorCode errorCode) {
        if (object != null) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object    the object to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text      the String to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text is empty
     * @see org.springframework.util.StringUtils#hasLength
     */
    public static void hasLength(String text, ErrorCode errorCode) {
        if (!org.springframework.util.StringUtils.hasLength(text)) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text      the String to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     * @see org.springframework.util.StringUtils#hasText
     */
    public static void hasText(String text, ErrorCode errorCode) {
        if (!StringUtils.hasText(text)) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array     the array to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array is {@code null} or contains no elements
     */
    public static void notEmpty(Object[] array, ErrorCode errorCode) {
        if (ObjectUtils.isEmpty(array)) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that an array contains no {@code null} elements. <p>Note: Does not complain if the
     * array is empty! <pre class="code">Assert.noNullElements(array, "The array must contain
     * non-null elements");</pre>
     *
     * @param array     the array to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, ErrorCode errorCode) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new ArgumentException(errorCode);
                }
            }
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @param errorCode  the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the collection is {@code null} or contains no elements
     */
    public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ArgumentException(errorCode);
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map     the map to check
     * @param errorCode the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the map is {@code null} or contains no entries
     */
    public static void notEmpty(Map<?, ?> map, ErrorCode errorCode) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ArgumentException(errorCode);
        }
    }
}
