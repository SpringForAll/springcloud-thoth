package com.prometheus.thoth.common.util;



import com.prometheus.thoth.common.enums.DateInterval;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类.
 *
 * @author liangliang
 * @since 2017/04/24
 */
public abstract class DateUtils {

    public static Date now() {
        return DateTime.now().toDate();
    }

    public static long nowMillis() {
        return DateTime.now().getMillis();
    }

    public static DateTime dateTime() {
        return DateTime.now();
    }

    /**
     * The format used is <tt>yyyy-MM-dd HH:mm:ss</tt>.
     */
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * The format used is <tt>yyyy-MM-dd HH:mm:ssZZ</tt>.
     */
    public static final String FORMAT_DATETIME_TIME_ZONE = "yyyy-MM-dd HH:mm:ssZZ";

    /**
     * The format used is <tt>yyyy-MM-dd</tt>.
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * The format used is <tt>HH:mm:ss</tt>.
     */
    public static final String FORMAT_TIME = "HH:mm:ss";

    /**
     * The format used is <tt>HH:mm:ssZZ</tt>.
     */
    public static final String FORMAT_TIME_NO_T_TIME_ZONE = "HH:mm:ssZZ";

    /**
     * The format used is <tt>yyyy</tt>.
     */
    public static final String FORMAT_YEAR = "yyyy";

    /**
     * The format used is <tt>yy</tt>.
     */
    public static final String FORMAT_YEAR_TWO = "yy";

    /**
     * The format used is <tt>MM</tt>.
     */
    public static final String FORMAT_MONTH = "MM";

    /**
     * The format used is <tt>M</tt>.
     */
    public static final String FORMAT_MONTH_ONE = "M";

    /**
     * The format used is <tt>yyyy-MM</tt>.
     */
    public static final String FORMAT_YEAR_MONTH = "yyyy-MM";

    /**
     * The format used is <tt>yyyy-MM</tt>.
     */
    public static final String FORMAT_HOURE_MINUTE = "HH:mm";

    /**
     * The format used is <tt>yyyy-MM</tt>.
     */
    public static final String FORMAT_DATE_HOURE_MINUTE = "yyy-MM-dd HH:mm";

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }


    /**
     * 根据时间区间格式化日期
     *
     * @param dateInterval
     * @param millis
     * @return
     */
    public static String getDateTimeView(DateInterval dateInterval, Long millis) {
        String result = "";
        DateTime dateTime = new DateTime(millis);
        switch (dateInterval) {
            case HOUR:
                result = dateTime.toString(DateUtils.FORMAT_DATE_HOURE_MINUTE);
                break;
            case DAY:
                result = dateTime.toString(DateUtils.FORMAT_DATE);
                break;
            case WEEK:
                int week = dateTime.getWeekOfWeekyear();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dateTime.toDate());

                result = String.format("%s年%s月第%s周", dateTime.getWeekyear(), dateTime
                        .getMonthOfYear(), calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
                break;
            case MONTH:
                result = dateTime.toString(DateUtils.FORMAT_YEAR_MONTH);
                break;
            case CURRENT_HOUR:
                result = dateTime.toString(DateUtils.FORMAT_HOURE_MINUTE);
                break;

        }
        return result;
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 格式化时间
     */
    public static String formatDate(Date date, String formate) {
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        return formatter.format(date);
    }

    public static Date parseDate(String dateStr, String formate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        return formatter.parse(dateStr);
    }

    public static Date formateDate(Date date, String formate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        String dateStr = formatter.format(date);
        return formatter.parse(dateStr);
    }
}
