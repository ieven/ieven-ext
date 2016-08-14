package com.ieven.ext.util.util;

import java.util.Collection;
import java.util.Map;

/**
 * 断言类，如果为false则抛出指定异常及信息
 * @author lichong
 *
 */
public abstract class Assert {

	/**
	 * 判断表达式是否为真，假则抛出IllegalArgumentException，描述信息为message
	 * @param expression
	 * @param message
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断表达式是否为真，假则抛出IllegalArgumentException，描述信息为默认的
	 * @param expression
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - 表达式必须为真");
	}

	/**
	 * 断言一个object是否为null，不为null则抛出IllegalArgumentException，描述信息为message
	 * @param object
	 * @param message
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言一个object是否为null，不为null则抛出IllegalArgumentException，描述信息为默认的
	 * @param object
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - 对象必须为空");
	}

	/**
	 * 断言一个对象是否为空 null,即未定义,如果为空则抛出IllegalArgumentException异常，并自定义发生异常时抛出信息
	 * @param object 需要检查的对象
	 * @param message 发生异常时抛出的异常信息
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言一个对象是否为空 null,即未定义,如果为空则抛出IllegalArgumentException异常，并抛出默认信息
	 * @param object
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - 对象不能为空");
	}

	/**
	 * 断言一个字符型对象是否不为空，为空则抛出自定义异常信息
	 * @param text
	 * @param message
	 */
	public static void hasLength(String text, String message) {
		if (!StringUtils.hasLength(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void hasLength(String text) {
		hasLength(text,
				"[Assertion failed] - 字符串对象不能为空");
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be {@code null} and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text, String message) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be {@code null} and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text) {
		hasText(text,
				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
	 * @param textToSearch the text to search
	 * @param substring the substring to find within the text
	 * @param message the exception message to use if the assertion fails
	 */
	public static void doesNotContain(String textToSearch, String substring, String message) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
				textToSearch.contains(substring)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * <pre class="code">Assert.doesNotContain(name, "rod");</pre>
	 * @param textToSearch the text to search
	 * @param substring the substring to find within the text
	 */
	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				"[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
	}


	/**
	 * Assert that an array has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(Object[] array, String message) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a {@code null} element
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array contains a {@code null} element
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the collection is {@code null} or has no elements
	 */
	public static void notEmpty(Collection collection, String message) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @throws IllegalArgumentException if the collection is {@code null} or has no elements
	 */
	public static void notEmpty(Collection collection) {
		notEmpty(collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a Map has entries; that is, it must not be {@code null}
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is {@code null} or has no entries
	 */
	public static void notEmpty(Map map, String message) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be {@code null}
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map);</pre>
	 * @param map the map to check
	 * @throws IllegalArgumentException if the map is {@code null} or has no entries
	 */
	public static void notEmpty(Map map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}


	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param clazz the required class
	 * @param obj the object to check
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param type the type to check against
	 * @param obj the object to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context. It should
	 * normally end in a ": " or ". " so that the function generate message looks
	 * ok when prepended to it.
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(
					(StringUtils.hasLength(message) ? message + " " : "") +
					"Object of class [" + (obj != null ? obj.getClass().getName() : "null") +
					"] must be an instance of " + type);
		}
	}

	/**
	 * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
	 * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
	 * @param superType the super type to check
	 * @param subType the sub type to check
	 * @throws IllegalArgumentException if the classes are not assignable
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType) {
		isAssignable(superType, subType, "");
	}

	/**
	 * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
	 * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
	 * @param superType the super type to check against
	 * @param subType the sub type to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context. It should
	 * normally end in a ": " or ". " so that the function generate message looks
	 * ok when prepended to it.
	 * @throws IllegalArgumentException if the classes are not assignable
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}

	/**
	 * 断言一个布尔型的表达式。如果表达式为false则抛出内容为message的IllegalStateException的异常
	 * @param expression 需要进行断言的表达式
	 * @param message 发生异常是抛出的异常信息
	 */
	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * 断言一个布尔型的表达式。如果表达式为false则抛出默认内容的IllegalStateException的异常
	 * @param expression
	 */
	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - 表达式状态必须为true");
	}

}
