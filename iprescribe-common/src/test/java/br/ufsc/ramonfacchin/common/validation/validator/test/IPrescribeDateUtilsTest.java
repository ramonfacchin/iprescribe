/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.validator.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;

/**
 * Test: Utility class to deal with Dates.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Mai 17, 2013
 * 
 */
public class IPrescribeDateUtilsTest {

	@Test
	public void testCreateDate() throws Exception {
		Date date = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 0, 0, 0, 1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Assert.assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		Assert.assertEquals(2012, cal.get(Calendar.YEAR));
		Assert.assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, cal.get(Calendar.MINUTE));
		Assert.assertEquals(0, cal.get(Calendar.SECOND));
		Assert.assertEquals(1, cal.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void testFormatDate() throws Exception {
		Date date = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 0, 0, 0, 1);
		String formatted = IPrescribeDateUtils.formatDate(null, date);
		Assert.assertEquals("01/01/2012 00:00:00.001", formatted);
	}

	@Test
	public void testFormatDateXMLGregorianCalendar() throws Exception {
		
	}

	@Test
	public void testParseDate() throws Exception {
		Date expected = IPrescribeDateUtils.createDate(2, Calendar.DECEMBER, 2013, 23, 59, 0, 0);
		Assert.assertEquals(expected, IPrescribeDateUtils.parseDate("02/12/2013 23:59:00.000", null));
	}

	@Test
	public void testParseDateXMLGregorianCalendar() throws Exception {
		
	}

	@Test
	public void testParseDateIgnoreTZ() throws Exception {
		
	}

	@Test
	public void testAddTime() throws Exception {
		Date date = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 0, 0, 1, 0);
		Date dateExpectedDay = IPrescribeDateUtils.createDate(3, Calendar.JANUARY, 2012, 0, 0, 1, 0);
		Date dateExpectedWeek = IPrescribeDateUtils.createDate(15, Calendar.JANUARY, 2012, 0, 0, 1, 0);
		Date dateExpectedHour = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 12, 0, 1, 0);
		Date dateExpectedMinute = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 0, 12, 1, 0);
		Date dateExpectedSecond = IPrescribeDateUtils.createDate(1, Calendar.JANUARY, 2012, 0, 0, 13, 0);
		Date dateExpectedSubtract = IPrescribeDateUtils.createDate(31, Calendar.DECEMBER, 2011, 0, 0, 1, 0);
		
		Assert.assertEquals(dateExpectedDay, IPrescribeDateUtils.addDays(date, 2));
		Assert.assertEquals(dateExpectedWeek, IPrescribeDateUtils.addWeeks(date, 2));
		Assert.assertEquals(dateExpectedHour, IPrescribeDateUtils.addHours(date, 12));
		Assert.assertEquals(dateExpectedMinute, IPrescribeDateUtils.addMinutes(date, 12));
		Assert.assertEquals(dateExpectedSecond, IPrescribeDateUtils.addSeconds(date, 12));
		Assert.assertEquals(dateExpectedSubtract, IPrescribeDateUtils.addDays(date, -1));
		
	}
	
}
