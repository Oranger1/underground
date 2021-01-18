package cn.oranger.cs.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateUtils {
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String DATE_FORMAT_UTC_HHMM = "yyyy-MM-dd'T'HH:mm'Z'";

    public static final String DATE_FORMAT_UTC_HH = "yyyy-MM-dd'T'HH'Z'";

    public static final String DATE_FORMAT_UTC_T = "yyyy-MM-dd'T'HH:mm:ss";

    public static DateTimeFormatter getFullFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
    }

    public static String parseToString(LocalDateTime dateTime) {
        return dateTime.format(getFullFormatter());
    }

    public static LocalDateTime parseToLocalDateTime(String date) {
        return LocalDateTime.parse(date, getFullFormatter());
    }

    public static Date parseToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getCurDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String getCurDateTimeFull() {
        return getCurDateTime(DATE_FORMAT_FULL);
    }

    public static String getCurDateYYYYMMDD() {
        return getCurDateTime(DATE_FORMAT_YYYY_MM_DD);
    }

    public static String getTimeYYYYMM() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM));
    }

    public static String getTimeYYYYMM(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM));
    }

    public static String getTimeYYYYMMDD(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD));
    }

    /**
     * 日期转字符串
     *
     * @return String
     * @author
     */
    public static String parseDateToString(Date thedate, String format) {
        if (thedate != null) {
            Instant instant = thedate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * parseDateToString(Date thedate, String format)的重载方法
     *
     * @param thedate
     * @return
     */
    public static String parseDateToString(Date thedate) {
        return parseDateToString(thedate, DATE_FORMAT_FULL);
    }

    public static Date parseStrToDate(String date) throws ParseException {
        return parseStrToDate(date, DATE_FORMAT_FULL);
    }

    /**
     * 字符串转日期
     *
     * @return Date
     * @author
     * @throws ParseException
     */
    public static Date parseStrToDate(String date, String format) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static String afterNMonthDate(String theDate, int month, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter).plusMonths(month).format(dateTimeFormatter);

    }

    public static String afterNMonthDate(String theDate, int month) {
        return afterNMonthDate(theDate, month, DATE_FORMAT_FULL);
    }

    public static Date afterNMonthDate(Date date, int months) {
        LocalDateTime time = toLocalDateTime(date).plusMonths(months);
        return toDate(time);
    }

    public static Date afterNWeekDate(Date date, int weeks) {
        LocalDateTime time = toLocalDateTime(date).plusWeeks(weeks);
        return toDate(time);
    }

    public static Date afterNDayDate(Date date, int days) {
        LocalDateTime time = toLocalDateTime(date).plusDays(days);
        return toDate(time);
    }

    public static String afterNDaysDate(String theDate, Integer nDayNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter).plusDays(nDayNum).format(dateTimeFormatter);
    }

    public static String afterNDaysDate(String theDate, Integer nDayNum) {
        return afterNDaysDate(theDate, nDayNum, DATE_FORMAT_FULL);
    }

    public static String afterNHoursDate(String theDate, Integer nHourNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter).plusHours(nHourNum).format(dateTimeFormatter);
    }

    public static String afterNHoursDate(String theDate, Integer nHourNum) {
        return afterNHoursDate(theDate, nHourNum, DATE_FORMAT_FULL);
    }

    public static String afterNMinsDate(String theDate, Integer nMinNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate, dateTimeFormatter).plusMinutes(nMinNum).format(dateTimeFormatter);
    }

    public static long getSubMonths(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    public static long getSubWeeks(String start, String end) {
        LocalDateTime startDate = LocalDateTime.parse(start, getFullFormatter());
        LocalDateTime endDate = LocalDateTime.parse(end, getFullFormatter());
        return getSubWeeks(startDate, endDate);
    }

    public static long getSubWeeks(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.WEEKS.between(start, end);
    }

    /**
     * 方法描述: 计算两个日期相差天数
     *
     * @param end
     *            结束日期
     * @param start
     *            开始日期
     */
    public static long getSubDays(String start, String end) {
        LocalDateTime startDate = LocalDateTime.parse(start, getFullFormatter());
        LocalDateTime endDate = LocalDateTime.parse(end, getFullFormatter());
        return getSubDays(startDate, endDate);
    }

    public static long getSubDays(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    public static long getSubHours(String start, String end) {
        LocalDateTime startDate = LocalDateTime.parse(start, getFullFormatter());
        LocalDateTime endDate = LocalDateTime.parse(end, getFullFormatter());
        return getSubHours(startDate, endDate);
    }

    public static long getSubHours(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    public static long getSubMinutes(String start, String end) {
        LocalDateTime startDate = LocalDateTime.parse(start, getFullFormatter());
        LocalDateTime endDate = LocalDateTime.parse(end, getFullFormatter());
        return getSubMinutes(startDate, endDate);
    }

    public static long getSubMinutes(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

    public static long getSubSeconds(String start, String end) {
        LocalDateTime startDate = LocalDateTime.parse(start, getFullFormatter());
        LocalDateTime endDate = LocalDateTime.parse(end, getFullFormatter());
        return getSubSeconds(startDate, endDate);
    }

    public static long getSubSeconds(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * 获取上个整点
     * 
     * @param dateTime
     * @return
     */
    public static String getLastWholeHourForString(LocalDateTime dateTime) {
        LocalDateTime lastTime = getLastWholeHour(dateTime);
        return lastTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    public static LocalDateTime getLastWholeHour(LocalDateTime dateTime) {
        LocalDateTime time = parseToLocalDateTime(dateTime.format(getFullFormatter()));
        return time.minusSeconds(time.getSecond()).minusMinutes(time.getMinute());
    }

    public static LocalDateTime getLastWholeDay(LocalDateTime dateTime) {
        return getLastWholeHour(dateTime).minusHours(dateTime.getHour());
    }

    public static Date getLastWholeDay(Date dateTime) {
        LocalDateTime localDateTime = toLocalDateTime(dateTime);
        return parseToDate(getLastWholeDay(localDateTime));
    }

    public static LocalDateTime getLastWholeDay(LocalDateTime dateTime, Integer days) {
        return getLastWholeHour(dateTime).minusHours(dateTime.getHour()).minusDays(days);
    }

    public static Date getLastWholeDay(Date dateTime, Integer days) {
        LocalDateTime localDateTime = toLocalDateTime(dateTime);
        LocalDateTime lastWholeDay = getLastWholeDay(localDateTime, days);
        return parseToDate(lastWholeDay);
    }

    /**
     * 获取下个整点
     * 
     * @param dateTime
     * @return
     */
    public static String getNextWholeHourForString(LocalDateTime dateTime) {
        return getNextWholeHour(dateTime).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    public static String getNextWholeHourForString(String dateTime) {
        return getNextWholeHour(parseToLocalDateTime(dateTime)).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    public static LocalDateTime getNextWholeHour(LocalDateTime dateTime) {
        return getLastWholeHour(dateTime).plusHours(1);
    }

    public static LocalDateTime getNextWholeHour(String dateTime) {
        return getLastWholeHour(parseToLocalDateTime(dateTime)).plusHours(1);
    }

    public static LocalDateTime getNextWholeDay(LocalDateTime dateTime) {
        return getLastWholeDay(dateTime).plusDays(1);
    }

    public static LocalDateTime getNextWholeDay(String dateTime) {
        return getLastWholeDay(parseToLocalDateTime(dateTime)).plusDays(1);
    }

    public static Date getNextWholeDay(Date dateTime) {
        LocalDateTime localDateTime = toLocalDateTime(dateTime);
        return parseToDate(getNextWholeDay(localDateTime));
    }

    public static Date getNextWholeDay(Date dateTime, Integer days) {
        LocalDateTime localDateTime = toLocalDateTime(dateTime);
        return parseToDate(getNextWholeDay(localDateTime, days));
    }

    public static LocalDateTime getNextWholeDay(LocalDateTime dateTime, Integer days) {
        return getLastWholeDay(dateTime).plusDays(days);
    }

    public static String getNextWholeDayForString(LocalDateTime dateTime) {
        return getNextWholeDay(dateTime).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    public static String getNextWholeDayForString(String dateTime) {
        return getNextWholeDay(parseToLocalDateTime(dateTime)).format(DateTimeFormatter.ofPattern(DATE_FORMAT_FULL));
    }

    public static LocalDateTime getCurrWeekFirstDay() {
        LocalDateTime now = getLastWholeDay(LocalDateTime.now());
        return now.minusDays(now.getDayOfWeek().getValue() - 1);
    }

    public static LocalDateTime getCurrMonthFirstDay() {
        LocalDateTime now = getLastWholeDay(LocalDateTime.now());
        return now.minusDays(now.getDayOfMonth() - 1);
    }

    public static LocalDateTime getCurrYearFirstDay() {
        LocalDateTime now = getLastWholeDay(LocalDateTime.now());
        return now.minusDays(now.getDayOfYear() - 1);
    }

    /**
     * 将Date转为LocalDateTime
     * 
     * @param date
     * @return
     */
    public static LocalDateTime formatterDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static String formatToUtcHHmm(Date date) {
        return formatToUtc(date, DATE_FORMAT_UTC_HHMM);
    }

    public static String utcToLocalString(String date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_UTC);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date utcDate = sdf.parse(date);
            SimpleDateFormat localFormater = new SimpleDateFormat(DATE_FORMAT_FULL);
            localFormater.setTimeZone(TimeZone.getDefault());
            return localFormater.format(utcDate.getTime());
        } catch (ParseException e) {
            log.warn("utcToLocalString error : {}", e.getMessage());
        }
        return null;
    }

    public static Date utcToLocalDate(String date) {
        try {
            return parseStrToDate(utcToLocalString(date));
        } catch (ParseException e) {
            log.warn("utcToLocalDate error : {}", e.getMessage());
        }
        return null;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZonedDateTime zoneDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zoneDateTime.toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime parseUtcToLocalDateTime(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        boolean containsT = str.contains("T");
        boolean containsZ = str.contains("Z");

        DateTimeFormatter format = null;

        if (str.length() == 14 && containsT && containsZ) {
            format = DateTimeFormatter.ofPattern(DATE_FORMAT_UTC_HH);
        }

        if (str.length() == 17 && containsT && containsZ) {
            format = DateTimeFormatter.ofPattern(DATE_FORMAT_UTC_HHMM);
        }

        if (str.length() == 19 && !containsT && !containsZ) {
            format = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
        }

        if (str.length() == 19 && containsT && !containsZ) {
            format = DateTimeFormatter.ofPattern(DATE_FORMAT_UTC_T);
        }

        if (str.length() == 20 && containsT && containsZ) {
            format = DateTimeFormatter.ofPattern(DATE_FORMAT_UTC);
        }

        if (format == null) {
            return null;
        }

        return LocalDateTime.parse(str, format).plusHours(8);
    }

    public static Date parseUtcTimeStampToDate(String timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        String date = parseDateToString(new Date(new Long(timeStamp)), DATE_FORMAT_UTC);
        return parseUtcToDate(date);
    }

    public static Date parseUtcToDate(String str) {
        return toDate(parseUtcToLocalDateTime(str));
    }

    public static String parseUtcToString(String str) {
        return parseUtcToString(str, DATE_FORMAT_FULL);
    }

    public static String parseUtcToString(String str, String pattern) {
        LocalDateTime localDateTime = parseUtcToLocalDateTime(str);
        return format(localDateTime, pattern);
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatToUtc(Date date) {
        return formatToUtc(date, DATE_FORMAT_UTC);
    }

    public static String formatToUtc(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = toLocalDateTime(date);
        return format(localDateTime.minusHours(8), pattern);
    }

//    public static long getSubDays(Date start, Date end) {
//        return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
//    }
//
//    public static long getSubSeconds(Date start, Date end) {
//        return Seconds.secondsBetween(new DateTime(start), new DateTime(end)).getSeconds();
//    }
}
