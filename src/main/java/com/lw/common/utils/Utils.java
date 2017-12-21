/**
 *
 */
package com.lw.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author
 * @version 创建时间：2016年10月27日 下午5:17:06
 * 说明:
 */

/**
 * @author chenbb
 *
 */
public class Utils {

	static long getTicks() {
		Date dt = new Date();
		Long time = dt.getTime();// 这就是距离1970年1月1日0点0分0秒的毫秒数
		return time;
	}

	public static String getSelectSQLWithPage(String strFullSQL, int iBegin, int iEnd) {
		String strPageSQL = "select * from (select row_.*, rownum ob_rownum_  from (" + strFullSQL
				+ ") row_  where rownum <=" + iEnd + ") where ob_rownum_ >=" + iBegin;
		return strPageSQL;
	}

	public static boolean judgeNum(Integer num) {
		if (num != null && num != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(Double num) {
		if (num != null && num != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(BigDecimal num) {
		if (num != null && num.compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeNum(Long num) {
		if (num != null && num != 0) {
			return true;
		}
		return false;
	}

	public static boolean judgeStr(String str) {
		if (str != null && !str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static String getGeometry(Double longitude, Double latitude, BigDecimal gridId) {
		// if (gridId == null)
		// return null;
		return "{\"type\": \"Point\", \"coordinates\": [" + longitude + "," + latitude + "]}";
	}

	public static BigDecimal subDecimal(Integer pos, BigDecimal num) {
		if (num == null)
			return null;

		// if (length(num) != 15)
		// return null;
		if (length(num) < 12)
			return null;

		String str = num.toString();
		return new BigDecimal(str.substring(0, pos));
	}

	public static Integer length(BigDecimal num) {
		if (num == null)
			return -999999;
		String str = num.toString();
		return str.length();
	}


	public static Boolean equal(BigDecimal a, BigDecimal b) {
		if (a != null && a.compareTo(b) == 0)
			return true;
		return false;
	}

	public static Boolean equal(BigDecimal a, Long b) {
		if (a != null && a.compareTo(new BigDecimal(b)) == 0)
			return true;
		return false;
	}

	public static Boolean equal(Long a, BigDecimal b) {
		if (a != null && (new BigDecimal(a)).compareTo(b) == 0)
			return true;
		return false;
	}

	public static boolean judgeList(List<?> list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public static String sendPostRequest(String strUrl, String data) throws IOException {

		// Build parameter string

		// Send the request
		URL url = new URL(strUrl);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		// write parameters
		writer.write(data);
		writer.flush();

		// Get the response
		StringBuffer answer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			answer.append(line);
		}
		writer.close();
		reader.close();
		return answer.toString();

	}

	static public String getIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String erase(String str) {
		return str = str.substring(0, str.length() - 1);
	}

	public static String getGridSql(BigDecimal id) {
		String sql = "";
		if (Utils.length(id) == 15) {
			sql += " AND grid_id = ? ";
		} else if (Utils.length(id) == 12) {
			sql += " AND community_id = ? ";
		} else if (Utils.length(id) == 9) {
			sql += " AND township_id = ? ";
		}
		return sql;
	}

	public static Boolean contain(BigDecimal a, BigDecimal b) {
		String aStr = a.toString();
		String bStr = b.toString();
		return aStr.contains(bStr);
	}

	public static BigDecimal getRealAreaId(BigDecimal townshipId, BigDecimal communityId, BigDecimal gridId) {
		if (gridId != null) {
			return gridId;
		}

		if (communityId != null) {
			return communityId;
		}

		if (gridId != null) {
			return gridId;
		}

		return null;
	}

	public static String getDatePoor(Long aTime, Long bTime) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = aTime - bTime;
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}

	public static String division(Long a, Long b) {
		String result = "";
		float num = (float) a / b;
		DecimalFormat df = new DecimalFormat("0.0");
		result = df.format(num);
		return result;
	}

	public static String timestampToDate(String timeStamp) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeStampL = new Long(timeStamp);
		Date date = new Date(timeStampL);
		res = simpleDateFormat.format(date);
		return res;
	}

	public static long getCurrentTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static long getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}

	public static long getQuarterFirstDay() {
		Calendar calendar = Calendar.getInstance();
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH) + 1, true);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}

	private static int getQuarterInMonth(int month, boolean isQuarterStart) {
		int[] months = { 1, 4, 7, 10 };
		if (!isQuarterStart) {
			months = new int[] { 3, 6, 9, 12 };
		}
		if (month >= 1 && month <= 3) {
			return months[0];
		} else if (month >= 4 && month <= 6) {
			return months[1];
		} else if (month >= 7 && month <= 9) {
			return months[2];
		} else {
			return months[3];
		}
	}

	public static long getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}

    public static Long excelTime(String excelTime){
        if (excelTime == null || excelTime.equals(""))
        {
            return null;
        }
        Calendar c = new GregorianCalendar(1900,0,-1);  
        Date start = c.getTime();  
        Date time = DateUtils.addDays(start, Integer.parseInt(excelTime));
        return time.getTime();
    }	
    
    public static String writeToExcelTime(Long excelTime){ 
        return new SimpleDateFormat("yyyy-MM-dd").format(excelTime);
    }
}
