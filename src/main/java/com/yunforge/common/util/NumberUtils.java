package com.yunforge.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
	private static String defaultPattern = "0.00";

	public static String fmtNumber(Object v) {
		if (v == null)
			return "";
		return new DecimalFormat(defaultPattern).format(v).toString();
	}

	public static String fmtNumber(Object v, String pattern) {
		if (v == null)
			return "";
		return new DecimalFormat(pattern).format(v).toString();
	}

	public static double round(Object v) {
		if (v == null)
			return 0.0D;
		BigDecimal b = new BigDecimal(v.toString());
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, 4).doubleValue();
	}

	public static double round(Object v, int scale) {
		if (v == null)
			return 0.0D;
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v.toString());
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, 4).doubleValue();
	}

	public static int intRound(Object v) {
		if (v == null)
			return 0;
		BigDecimal b = new BigDecimal(v.toString());
		BigDecimal one = new BigDecimal("1");
		return (int) b.divide(one, 0, 4).doubleValue();
	}

	public static double myRound(double v) {
		String s1 = String.valueOf(v);
		double returnval = 0.0D;
		int index = s1.indexOf(".");
		if (index > 0) {
			s1 = s1.substring(0, index + 2);
			returnval = Double.parseDouble(s1);
		} else {
			returnval = v;
		}
		return returnval;
	}

	public static void main(String[] args) {
		double a = 123.67567889999999D;
		double b = round(Double.valueOf(a), 5);
		System.out.println(b);
		System.out.println(myRound(a));
	}
}