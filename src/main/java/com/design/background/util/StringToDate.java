package com.design.background.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 日期字符串转换成java.sql.Date
 * @author 孟晓冬
 *
 */
public class StringToDate {
	/**
	 * 日期字符串转换成java.sql.Date
	 * @author 孟晓冬
	 *
	 */
	public static Date toSqlDate(String time){
		//检测purchaseTime格式, 是否为空 
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	    java.util.Date d = null;
	    try {  
	        d = format.parse(time);
	    } catch (Exception e) {
	        d=null;
	    }  
	    //java.util.Date转java.sql.Date
	    java.sql.Date date=null;
	    if(d==null) {
            date = null;
        } else {
            date = new Date(d.getTime());
        }
	    return date;
	}
	/**
	 * 日期字符串转换成java.util.Date
	 * @author 孟晓冬
	 *
	 */
	public static java.util.Date toUtilDate(String time){
		//检测purchaseTime格式, 是否为空 
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	    java.util.Date d = null;
	    try {  
	        d = format.parse(time);
	    } catch (Exception e) {
	        d=null;
	    }  
	    return d;
	}

	
}
