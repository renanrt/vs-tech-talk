package br.com.videosoft.pinpad.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String fitToSize(String text, int size) {
		if (text.length() <= size) {
			return StringUtils.rightPad(text, size);
		} else {
			return StringUtils.left(text, size);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(fitToSize("12345678901111111111111111111", 10));
	}
	
}
