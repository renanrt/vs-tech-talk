package br.com.videosoft.pinpad.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtil {

	private static Map<String, String> meses = new HashMap<String, String>();

	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.DAY_OF_MONTH, 18);
		c1.set(Calendar.MONTH, 7);
		c1.set(Calendar.YEAR, 2011);
		System.out.println(formatBrazilianWrittenMonthYear(getSystemDate()));
	}
	
	static {
		meses.put("01", "Janeiro");
		meses.put("02", "Fevereiro");
		meses.put("03", "Março");
		meses.put("04", "Abril");
		meses.put("05", "Maio");
		meses.put("06", "Junho");
		meses.put("07", "Julho");
		meses.put("08", "Agosto");
		meses.put("09", "Setembro");
		meses.put("10", "Outubro");
		meses.put("11", "Novembro");
		meses.put("12", "Dezembro");
	}

	public static String FMT_DD_MM_YYYY_hh_mm_ss = "dd/MM/yyyy HH:mm:ss";
	public static String FMT_DD_MM_YYYY = "dd/MM/yyyy";
	public static String FMT_DD_MM_YY = "dd/MM/yy";
	public static String FMT_MM_YYYY = "MM/yyyy";
	public static String FMT_MMM_YYYY = "MMM/yyyy";

	public static long mesesEntre(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		// Set time
		c1.setTime(d1);
		c2.setTime(d2);

		// Calcula a diferen�a entre as datas
		long diferenca = c2.getTimeInMillis() - c1.getTimeInMillis();

		// Quantidade de milissegundos em um dia
		int tempoDia = 1000 * 60 * 60 * 24;

		long diasDiferenca = diferenca / tempoDia / 30;
		return diasDiferenca;
	}


	public static int diasEntre(Date d1, Date d2) {
		return (int) ((trunc(d2).getTime() - trunc(d1).getTime()) / (24 * 60 * 60 * 1000));
	}
	
	public static boolean isDayBetween(Date d, Date d1, Date d2) {
		return d1.compareTo(d) * d.compareTo(d2) >= 0;
	}

	public static int lastDayOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date setLastDayOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	public static Date changeDay(Date date, int day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	public static Date addMin(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}

	public static Date addDay(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}
	
	/**
	 * Format like: dd/mm/yyyy
	 * @param date
	 * @return formated date
	 */
	public static String formatBrazilian(Date date) {
		return format(date, FMT_DD_MM_YYYY);
	}
	
	/**
	 * Format like: dd/mm/yy
	 * @param date
	 * @return formated date
	 */
	public static String formatBrazilianShort(Date date) {
		return format(date, FMT_DD_MM_YY);
	}
	
	public static String getTodayFormatBrazilianWithTime() {
		return formatBrazilianWithTime(new Date());
	}
	
	/**
	 * Format as month/Year in the format: mon/YYYY. Example: Jan/2011.
	 * @param date
	 * @return
	 */
	public static String formatBrazilianWrittenMonthYear(Date date) {
		return format(date, FMT_MMM_YYYY);
	}
	
	
	public static String formatBrazilianWithTime(Date date) {
		return format(date, FMT_DD_MM_YYYY_hh_mm_ss);
	}

	public static String format(Date date, String format) {
		if (date != null && format != null) {
			Locale ptLocale = new Locale("pt");
			DateFormat df = new SimpleDateFormat(format,ptLocale );
			return df.format(date);
		}
		return null;
	}

	public static Date parse(String date, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		return df.parse(date);
	}

	public static String getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;

		if (month < 10) {
			return "0" + month;
		} else {
			return String.valueOf(month);
		}
	}

	public static int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int month = cal.get(Calendar.DAY_OF_MONTH);
		return month;
	}

	public static String getMesAno(Date date) {
		String mesAno = getMonth(date) + getYear(date);
		return mesAno;
	}

	public static int getYear(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static String getShortMonth(String month) {
		String shortMonth = meses.get(month);
		return shortMonth.substring(0, 3).toUpperCase();

	}

	public static String getComplete(String month) {
		return meses.get(month);
	}

	public static Date getSystemDate() {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(System.currentTimeMillis());
		return cal.getTime();
	}
	
	public static long diffInMinutesAbsFromNow(Date d1) {
		return diffInMinutesAbs(d1,getSystemDate() );
	}
	
	public static long diffInMinutesAbs(Date d1, Date d2) {
		long diffInSeconds = diffInSecondsAbs(d1, d2);
		return diffInSeconds/60;
	}
	
	public static long diffInMinutes(Date d1, Date d2) {
		long diffInSeconds = diffInSeconds(d1, d2);
		return diffInSeconds/60;
	}
	
	public static long diffInHours(Date d1, Date d2) {
		long diffInMinutes = diffInMinutes(d1, d2);
		return diffInMinutes/60;
	}
	
	public static long diffInHoursAbs(Date d1, Date d2) {
		long diffInMinutes = diffInMinutesAbs(d1, d2);
		return diffInMinutes/60;
	}

	public static long diffInSeconds(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		long diffInMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
		return (long) (diffInMillis / 1000);
	}
	
	public static long diffInSecondsAbs(Date d1, Date d2) {
		return Math.abs(diffInSeconds(d1, d2));
	}

	public static Date trunc(Date data) {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date setFimDia(Date data) {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();
	}
	
	public static Date setAnoMaisProximo(Date data) {
		Calendar currentYear = Calendar.getInstance();
		currentYear.setTime(data);
		
		Calendar cToday = Calendar.getInstance();
		int thisYear = cToday.get(Calendar.YEAR);
		
		currentYear.set(Calendar.YEAR, thisYear);
		
		if(currentYear.after(setFimDia(cToday.getTime()))){
			Calendar lastYear = Calendar.getInstance();
			lastYear.setTime(data);
			lastYear.set(Calendar.YEAR, thisYear - 1);	
			return lastYear.getTime();
		}
		return currentYear.getTime();
	}

	public static Long diffDays(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		return diffDays(start, end);
	}

	public static Long diffDays(Calendar startDate, Calendar endDate) {
		long daysBetween = 0;

		startDate = clearTimeDate(startDate);
		endDate = clearTimeDate(endDate);

		for (; startDate.before(endDate); daysBetween++) {
			startDate.add(Calendar.DAY_OF_MONTH, 1);
		}

		return daysBetween;
	}

	/**
	 * Clear a Time of a Calendar.
	 * 
	 * @param date
	 *            the date to clear the time.
	 * @return Date the new date.
	 */
	public static Calendar clearTimeDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);

		return date;
	}

	/**
	 * Clear a Time of a Date.
	 * 
	 * @param date
	 *            the date to clear the time.
	 * @return Date the new date.
	 */
	public static Date clearTimeDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return clearTimeDate(cal).getTime();
	}

	/**
     * <p>Checks if two dates are on the same day ignoring time.</p>
     * @param date1  the first date, not altered, not null
     * @param date2  the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }
    

    /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}