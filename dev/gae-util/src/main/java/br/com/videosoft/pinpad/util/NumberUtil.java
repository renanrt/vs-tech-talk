package br.com.videosoft.pinpad.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class NumberUtil {

	private static DecimalFormat decimalFormatter;

	static{
		decimalFormatter = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt", "BR"));
		decimalFormatter.applyPattern("####.00");
	}
	
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(19);
		System.out.println(format(bigDecimal));
	}
	
	public static String format(BigDecimal n) {
		return decimalFormatter.format(n);
	}
	
	/**
	 * Converts a number to percentage. Use this method only to display purposes.
	 * @param n percentage number.
	 * @return something like 99,99%. If you pass as parameter 99.999, then 100 will be returned
	 */
	public static String formatPorcentagem(BigDecimal n) {
		if (n != null) {
			return decimalFormatter.format(n)+"%";
		}
		return null;
	}
	
	/**
	 * Converts a double to bigdecimal.
	 * @param value
	 * @return returns the number converted or null, if the parameter is null.
	 */
	public static BigDecimal asBigDecimal(Double value) {
		BigDecimal valueBigDecimal = null;
    	if(value!=null){
    		valueBigDecimal = BigDecimal.valueOf(value);
    	}
    	return valueBigDecimal;
	}

}
