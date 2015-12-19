package com.raincent.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * text文本的实用方法
 * @author DELL
 */
public class TextUtility
{
	/**
	 * 测试字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str)
	{
		return (null==str||0==str.length());
	}

	/**
	 * 补充html换行符
	 * @param str
	 * @return
	 */
	public static String subString(String str)
	{
		if (isNull(str))
		{
			return "";
		}
		else
		{
			str = str.substring(0, 50);
			str = str.replaceAll("\r\n", "，");
			str = str.replace("<p>", "");
			str = str.replace("</p>", "");
			str = str.replace("　　", "");
			str +="...";
			return str;
		}
	}
	
	/**
	 * 从字符串转换成整形
	 * @param str 待转换字符串 
	 * @return
	 */	
	public static int String2Int(String str)
	{
		try
		{
			int value = Integer.valueOf(str);
			return value;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 进行编码
	 * @param str
	 * @return
	 */
	public static String toUTF8(String str)
	{
		if (!isNull(str))
		{
			try
			{
				str = new String(URLDecoder.decode(str,"utf-8"));
//				str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static String formatDateStr(String dateStr)
	{
		String datetemp;
		if(dateStr.length()>19)
		{
		       datetemp=dateStr.substring(0,19);
		}else{
		      datetemp=dateStr;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(datetemp));
	}
	/**
	 * 格式化时间
	 * @param dateStr
	 * @return date 2015-02-07 14:01:16
	 * @throws ParseException
	 */
	public static String formatDate(String dateStr) throws ParseException
	{

		String datetemp;
		if(dateStr.length()>19)
		{
		       datetemp=dateStr.substring(0,19);
		}else{
		      datetemp=dateStr;
		}
//		String date=datetemp.replaceAll("-", "/");
		   return datetemp;
	}
}
