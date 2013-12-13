/**
 * 
 */
package br.ufsc.ramonfacchin.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import br.ufsc.ramonfacchin.common.constant.GlobalConstants;

/**
 * Utility class to deal with Dates.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Mai 17, 2013
 * 
 */
public class IPrescribeDateUtils {

	/**
	 * Formats the {@link Date} with the given pattern.
	 * 
	 * @param pattern (if no pattern given, defaults to dd/MM/yyyy HH:mm:ss.SSS)
	 * @param date
	 * @return resulting {@link String} from date formatting.
	 */
	public static String formatDate(String pattern, Date date) {
		if (date != null) {
			return new SimpleDateFormat(pattern == null ? "dd/MM/yyyy HH:mm:ss.SSS" : pattern).format(date);
		}
		return null;
	}

	/**
	 * Formats a {@link XMLGregorianCalendar} with the given pattern.
	 * 
	 * @param pattern
	 * @param date
	 * @return resulting {@link String} from date formatting.
	 */
	public static String formatDate(String pattern, XMLGregorianCalendar date) {
		if (date != null) {
			return new SimpleDateFormat(pattern).format(parseDate(date));
		}
		return null;
	}

	/**
	 * @param dateStr
	 * @param pattern if no pattern given, defaults to dd/MM/yyyy HH:mm:ss.SSS
	 * @return the parsed date for the given pattern or <code>null</code> if any error occurs
	 */
	public static Date parseDate(String dateStr, String pattern) {
		pattern = pattern == null ? "dd/MM/yyyy HH:mm:ss.SSS" : pattern;
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Converte uma variável {@link XMLGregorianCalendar} para {@link Date}.
	 * 
	 * @param date
	 * @return the converted {@link Date}.
	 */
	public static Date parseDate(XMLGregorianCalendar date) {
		if (date != null) {
			return date.toGregorianCalendar().getTime();
		}
		return null;
	}

	/**
	 * Converts a {@link XMLGregorianCalendar} to {@link Date}
	 * ignoring timezone differences.
	 * 
	 * @param date
	 * @return the converted {@link Date}.
	 */
	public static Date parseDateIgnoreTZ(XMLGregorianCalendar date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(date.getYear(), date.getMonth() - 1, date.getDay(), date.getHour(), date.getMinute(), date.getSecond());
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * @param date
	 * @param amount the amount of time to increase (in millis) (negative amount will decrease the time)
	 * @return the date increased in the given amount of time
	 */
	public static Date addTime(Date date, int amount) {
		long time = date.getTime();
		Calendar cal = Calendar.getInstance();
		time = time + amount;
		cal.setTimeInMillis(time);
		return cal.getTime();
	}
	
	/**
	 * @param date
	 * @param amount (negative amount will decrease the time)
	 * @return the date increased in the given amount of seconds
	 */
	public static Date addSeconds(Date date, int amount) {
		return addTime(date, amount*GlobalConstants.TIME.SECOND);
	}
	
	/**
	 * @param date
	 * @param amount (negative amount will decrease the time)
	 * @return the date increased in the given amount of minutes
	 */
	public static Date addMinutes(Date date, int amount) {
		return addTime(date, amount*GlobalConstants.TIME.MINUTE);
	}
	
	/**
	 * @param date
	 * @param amount (negative amount will decrease the time)
	 * @return the date increased in the given amount of hours
	 */
	public static Date addHours(Date date, int amount) {
		return addTime(date, amount*GlobalConstants.TIME.HOUR);
	}
	
	/**
	 * @param date
	 * @param amount (negative amount will decrease the time)
	 * @return the date increased in the given amount of days
	 */
	public static Date addDays(Date date, int amount) {
		return addTime(date, amount*GlobalConstants.TIME.DAY);
	}
	
	/**
	 * @param date
	 * @param amount (negative amount will decrease the time)
	 * @return the date increased in the given amount of weeks
	 */
	public static Date addWeeks(Date date, int amount) {
		return addTime(date, amount*GlobalConstants.TIME.WEEK);
	}
	
	/**
	 * 
	 * @param dayOfMonth
	 * @param month
	 * @param year
	 * @param hour24 (24h format hour)
	 * @param minute
	 * @param second
	 * @param millis
	 * @return a new {@link Date} with the given params
	 */
	public static Date createDate(int dayOfMonth, int month, int year, int hour24, int minute, int second, int millis) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.HOUR_OF_DAY, hour24);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millis);
		return cal.getTime();
	}
}
