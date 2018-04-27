package com.dxc.pai.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class CommonTool {

	/**
	 * change string to util date
	 * @param strDate
	 * @return
	 */
	public static java.util.Date strToUtilDate(String strDate) {
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date date = null;
	    try {
	        date = sf.parse(strDate);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return date;
	}
}
