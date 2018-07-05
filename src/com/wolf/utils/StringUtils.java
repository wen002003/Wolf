package com.wolf.utils;



import java.util.List;

/**
 * 提供针对字符串的简化操作。
 */
public class StringUtils
{
	/**
	 * 判断字符串是否为null。
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串为null时返回true
	 */
	public static boolean isNull( Object ... s )
	{
		for(int i=0;i<s.length;i++){  
			if (null==s[i])
				return true;
			} 
		return s == null;
	}

	/**
	 * 判断字符串是否不为null。
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串不为null时返回true
	 */
	public static boolean isNotNull( Object ... s )
	{
		return !isNull( s );
	}

	/**
	 * 判断字符串是否为null或空字符串。
	 * 
	 * <pre>
	 * isEmpty(null) = true
	 * isEmpty("") = true
	 * isEmpty("  ") = false
	 * isEmpty("foo") = false
	 * isEmpty(" foo ") = false
	 * </pre>
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串为null或空字符串时返回true
	 */
	public static boolean isEmpty( String s )
	{
		if ( s == null || s.length() == 0 )
			return true;
		return false;
	}

	/**
	 * 判断字符串是否不为null或空字符串。
	 * 
	 * <pre>
	 * isNotEmpty(null) = false
	 * isNotEmpty("") = false
	 * isNotEmpty("  ") = true
	 * isNotEmpty("foo") = true
	 * isNotEmpty(" foo ") = true
	 * </pre>
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串不为null或空字符串时返回true
	 */
	public static boolean isNotEmpty( String s )
	{
		return !isEmpty( s );
	}

	/**
	 * 判断字符串是否为null或空字符串或全空格字符串。
	 * 
	 * <pre>
	 * isBlank(null) = true
	 * isBlank("") = true
	 * isBlank("  ") = true
	 * isBlank("foo") = false
	 * isBlank(" foo ") = false
	 * </pre>
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串为null或空字符串或全空格字符串时返回true
	 */
	public static boolean isBlank( String s )
	{
		if ( isEmpty( s ) )
			return true;
		for ( int i = 0; i < s.length(); i++ )
			if ( Character.isWhitespace( s.charAt( i ) ) == false )
				return false;

		return true;
	}

	/**
	 * 判断字符串是否不为null或空字符串或全空格字符串。
	 * 
	 * <pre>
	 * isNotBlank(null) = false
	 * isNotBlank("") = false
	 * isNotBlank("  ") = false
	 * isNotBlank("foo") = true
	 * isNotBlank(" foo ") = true
	 * </pre>
	 * 
	 * @param s
	 *        要判断的字符串
	 * @return 字符串不为null或空字符串或全空格字符串时返回true
	 */
	public static boolean isNotBlank( String s )
	{
		return !isBlank( s );
	}

	/**
	 * 将数组转化为字符串，数组元素之间用给定的符号分割。
	 * 
	 * @param o
	 *        要分割的数组
	 * @param splitter
	 *        给定的分割符号
	 * @return 分割后的字符串。如果数组为空，返回空字符串而不是null
	 */
	public static String join( Object[] o , String splitter )
	{
		if ( o == null || o.length == 0 )
		{
			return "";
		}

		StringBuffer result = new StringBuffer();

		for ( int i = 0 , len = o.length; i < len; i++ )
		{
			result.append( o[i] );
			if ( i + 1 != len )
			{
				result.append( splitter );
			}
		}

		return result.toString();
	}

	/**
	 * 将列表转化为字符串，列表元素之间用给定的符号分割。
	 * 
	 * @param l
	 *        要分割的列表
	 * @param splitter
	 *        给定的分割符号
	 * @return 分割后的字符串。如果列表为空，返回空字符串而不是null
	 */
	public static String join( List l , String splitter )
	{
		if ( l == null || l.size() == 0 )
		{
			return "";
		}

		StringBuffer result = new StringBuffer();

		for ( int i = 0 , len = l.size(); i < len; i++ )
		{
			result.append( l.get( i ) );
			if ( i + 1 != len )
			{
				result.append( splitter );
			}
		}

		return result.toString();
	}

	/**
	 * 正则表达式匹配校验。
	 * 
	 * @param pattern 
	 *        正则表达式
	 * @param str
	 *        要校验则字符串
	 * @return 找到匹配，返true
	 */
	public static boolean patternMatcher( String pattern , String str )
	{
		return java.util.regex.Pattern.compile( pattern ).matcher( str ).matches();
	}

	/**
	 * 去除字符串两边的空格，如果给定的参数为null，则返回空字符串。
	 * 
	 * @param s 要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String trim( String s )
	{
		if ( isEmpty( s ) )
			return "";
		else
			return s.trim();
	}
}
