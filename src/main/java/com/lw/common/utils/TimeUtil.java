package com.lw.common.utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import org.apache.commons.lang3.StringUtils;
public class TimeUtil
{
    private TimeUtil()
    {
    }

    /**
     * Get current time.
     * 
     * @return
     */
    public static String getTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
    
    public static String getExportTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    /**
     * Get current time stamp.
     * 
     * @return
     */
    public static Timestamp getTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Convert time.
     * 
     * @param time
     * @return
     */
    public static String convertTime(long time)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Timestamp(time));
    }

/*    public static Date StringToDate(String str, String pattern)
    {
        if (StringUtils.isBlank(str))
        {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try
        {
            date = dateFormat.parse(str);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return date == null ? new Date() : date;
    }*/

    public static String dateToString(Date date, String pattern)
    {
        if (null == date)
        {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String dstr = "";
        try
        {
            dstr = dateFormat.format(date);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dstr;
    }

    /**
     * Get current time for pattern.
     * 
     * @return
     */
    public static String getCurrentTime(String pattern)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

    /**
     * Get current time for pattern.
     * 
     * @return
     */
    public static Date getCurrentTime()
    {
        return new Date();
    }

    public static Date getMonthDay(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }


    static SimpleDateFormat ymdhmiC = new SimpleDateFormat("yyyyMMddHHmm");

    public static String currTime() {
        return formatYMDMIS(getTimestamp());
    }

    private static String formatYMDMIS(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }

        return ymdhmiC.format(timestamp);
    }
}