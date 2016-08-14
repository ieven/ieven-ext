package com.ieven.ext.util.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * String工具类
 * @author lichong
 *
 */
public abstract class StringUtils {

	private static final String FOLDER_SEPARATOR = "/";

	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	private static final String TOP_PATH = "..";

	private static final String CURRENT_PATH = ".";

	private static final char EXTENSION_SEPARATOR = '.';

	/**
	 * 检测所给字符串是否为空或者未定义
	 * @param str 待检测字符串
	 * @return 检测结果
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 检查给定的CharSequence是否长度为0或未定义
	 * @param str 待检测的CharSequence
	 * @return 检测结果
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检测所给字符串不为null或者长度为0
	 * @param str 待检测字符串
	 * @return
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * 检查一个给定的字符串中是否含有的文本
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * @param str 待检测字符串
	 * @return
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查一个给定的字符串中是否含有的文本
	 * @param str 待检测的字符串
	 * @return
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * 检测所给CharSequence是否含有空格
	 * @param str 
	 * @return
	 */
	public static boolean containsWhitespace(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测所给字符串中是否含有空格
	 * @param str
	 * @return
	 */
	public static boolean containsWhitespace(String str) {
		return containsWhitespace((CharSequence) str);
	}

	/**
	 * 去除给定字符串前后的空格
	 * @param str 待去除空格的字符串
	 * @return
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除所给字符串中所有空格
	 * @param str
	 * @return
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * 去除字符串中开头空格
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 去除字符串中末尾空格
	 * @param str
	 * @return
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除字符串中给定字符开头位置，如“aababb” 'a'则返回"babb"
	 * @param str 待处理字符
	 * @param leadingCharacter 要去除的字符
	 * @return 处理过以后的字符串
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 去除字符串中给定字符结尾位置，如“aababba” 'a'则返回"aababb"
	 * @param str 待处理字符
	 * @param trailingCharacter 要去除的字符
	 * @return 处理过以后的字符串
	 */
	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}


	/**
	 * 检测字符串是否是以给定字符串开头
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 检测字符串是否以给定字符串结尾
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 检测给定字符串在给定的位置是否为给定的字符
	 * @param str
	 * @param index
	 * @param substring
	 * @return
	 */
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 统计特定字符串在给定字符串中出现次数
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 替换字符串中指定字符
	 * @param inString
	 * @param oldPattern
	 * @param newPattern
	 * @return
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
	 * 去除给定字符串中指定字符串
	 * @param inString
	 * @param pattern
	 * @return
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 在给定字符串中删除给定字符
	 * @param inString 
	 * @param charsToDelete
	 * @return
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串的第一个字母变为大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 将字符串第一个字母变为小写
	 * @param str
	 * @return
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * 获取文件名称 ,如："mypath/myfile.txt"  "myfile.txt".
	 * @param path
	 * @return
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * 获取文件后缀名，如："mypath/myfile.txt"  "txt".
	 * @param path
	 * @return
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}

	/**
	 * 去除后缀以后的文件名，如："mypath/myfile.txt"  "mypath/myfile".
	 * @param path
	 * @return
	 */
	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return path;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return path;
		}
		return path.substring(0, extIndex);
	}

	/**
	 * 统一化路径
	 * @param path 需要统一化的地址
	 * @return 统一后的路径
	 */
	public static String cleanPath(String path) {
		if (path == null) {
			return null;
		}
		//替换Windows地址符
		String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
		
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			//如果路径前缀有/说明他输错了，直接去掉
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains("/")) {
				prefix = "";
			}
			else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
			prefix = prefix + FOLDER_SEPARATOR;
			pathToUse = pathToUse.substring(1);
		}
		//将类似/core/../test路径修改为/test   begin
		
		String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
		
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;
		
		for (int i = pathArray.length - 1; i >= 0; i--) {
			String element = pathArray[i];
			if (CURRENT_PATH.equals(element)) {
			}
			else if (TOP_PATH.equals(element)) {
				tops++;
			}
			else {
				if (tops > 0) {
					tops--;
				}
				else {
					pathElements.add(0, element);
				}
			}
		}

		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}
		return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
	}

	/**
	 * 比较两个路径是否完全相等
	 * @param path1
	 * @param path2
	 * @return
	 */
	public static boolean pathEquals(String path1, String path2) {
		return cleanPath(path1).equals(cleanPath(path2));
	}

	/**
	 * 将给定字符串追加进给定数组中
	 * @param array
	 * @param str
	 * @return
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[] {str};
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 将两个String类型的数组连接成一个，如：{"myString2"}和{"myString1", "myString2"}，连接以后为{"myString2","myString1", "myString2"}
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 将两个String数组合并，重复元素替换,如：{"myString2"}和{"myString1", "myString2"}，合并结果{"myString2", "myString1"}，使用时注意顺序
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 数组排序，如：{"myString2", "myString1"}，转换结果：{"myString1", "myString2"}
	 * @param array
	 * @return
	 */
	public static String[] sortStringArray(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 将给定的集合复制进入数组,即将集合转换为数组
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 将数组元素去除首尾空格
	 * @param array
	 * @return
	 */
	public static String[] trimArrayElements(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * String数组去重，去除重复的元素
	 * @param array
	 * @return
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return array;
		}
		Set<String> set = new TreeSet<String>();
		for (String element : array) {
			set.add(element);
		}
		return toStringArray(set);
	}


	/**
	 * 根据给定的分隔符将String转换成String数组，如：tokenizeToStringArray("a,b , ,c", ",")转换结果为：[a, b, c]
	 * @param str 待转换字符串
	 * @param delimiters 分隔符
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * 根据给定的分隔符将String转换成String数组
	 * @param str 待处理字符串
	 * @param delimiters 分隔符
	 * @param trimTokens 分隔以后的数组元素是否去掉空格
	 * @param ignoreEmptyTokens 是否忽略空
	 * @return
	 */
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}

	/**
	 * 将字符串按照给定分隔符分隔为数组，如com/test/test转化为com,test,test 
	 * @param str 待转换字符串
	 * @param delimiter 分隔符
	 * @return 分隔完成以后的字符数组
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter) {
		return delimitedListToStringArray(str, delimiter, null);
	}

	/**
	 * 将字符串根据提供的分隔符分割为数组，并将其中不需要的字符创删掉
	 * @param str 待修改字符串
	 * @param delimiter 分隔符
	 * @param charsToDelete 需要删掉的字符
	 * @return
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}
		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
			}
		}
		else {
			int pos = 0;
			int delPos;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}

	public static int[] stringToInt(String[] str)
	{
		int result[] = new int[str.length];
		for(int i=0;i<str.length;i++)
		{
			result[i]=Integer.parseInt(str[i]);
		}
		return result;
	}
	
	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	public static Set<String> commaDelimitedListToSet(String str) {
		Set<String> set = new TreeSet<String>();
		String[] tokens = commaDelimitedListToStringArray(str);
		for (String token : tokens) {
			set.add(token);
		}
		return set;
	}

	public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	public static String collectionToDelimitedString(Collection<?> coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}
	
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}

}
