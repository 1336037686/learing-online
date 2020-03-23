package com.lyy.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * @author wsq
 * @Date 2019/12/6
 *
 */
public class DateUtil {

    /**
     * 系统默认 日期类型 yyyy-MM-dd
     */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

    /**
     * 时间 日期类型 HH:mm:ss
     */
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";

    /**
     * 日期时间 日期类型 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时期格式 yyyy-MM-dd
     */
    public static DateFormat dateformater;

    /**
     * 时间格式 HH:mm:ss
     */
    public static DateFormat timeformater;

    /**
     * 日期时间格式 yyyy-MM-dd HH:mm
     */
    public static DateFormat dateTimeformater;
    static {
        if (DateUtil.dateformater == null) {
            DateUtil.dateformater = new SimpleDateFormat(DateUtil.DATE_PATTERN_DEFAULT);
        }
        if (DateUtil.timeformater == null) {
            DateUtil.timeformater = new SimpleDateFormat(DateUtil.DATE_PATTERN_TIME);
        }

        if (DateUtil.dateTimeformater == null) {
            DateUtil.dateTimeformater = new SimpleDateFormat(DateUtil.DATE_PATTERN_DATETIME);
        }
    }

    /**
     * 构造方法私有化
     */
    private DateUtil() {
    }

    /**
     * 得到现在时间
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据 yyyy-MM-dd 字符串解析成相应的日期
     * @param strDate yyyy-MM-dd 格式的日期
     * @return 转换后的日期
     */
    public static Date parseDate(String strDate) {
        Date date = null;
        if (StringUtils.isNotBlank(strDate)) {
            try {
                date = DateUtil.dateformater.parse(strDate);
            } catch (final Exception e) {
                e.printStackTrace();
                return date;
            }
        }
        return date;
    }

    /**
     * 根据 HH:mm:ss 字符串解析成相应的时间格式
     * @param strTime HH:mm:ss 格式的时间格式
     * @return 转换后的时间
     */
    public static Date parseTime(String strTime) {
        Date date = null;
        try {
            date = DateUtil.timeformater.parse(strTime);
        } catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     * 根据yyyy-MM-dd HH:mm字符串解析成相应的日期时间
     * @param strDateTime 以"yyyy-MM-dd HH:mm:ss"为格式的时间字符串
     * @return 转换后的日期
     */
    public static Date parseDateTime(String strDateTime) {
        Date date = new Date();
        try {
            date = DateUtil.dateTimeformater.parse(strDateTime);
        } catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     * 获取系统当前时间
     *
     * @return 系统当前时间
     */
    public static Date getCurrentDate() {
        final Calendar gc = Calendar.getInstance();
        return gc.getTime();
    }

    /**
     * 日期格式化
     * @param date 日期
     * @param expression 格式化 例如：yyyy-MM-dd
     * @return 格式化后的字符串
     */
    public static String parseDate(Date date, String expression){
        SimpleDateFormat sdf = new SimpleDateFormat(expression);
        return sdf.format(date);
    }


}



