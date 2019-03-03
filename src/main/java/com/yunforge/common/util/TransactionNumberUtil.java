package com.yunforge.common.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class TransactionNumberUtil {

	private static Date BUILD_DATE = new Date();

	private static long CURRENT_NUMBER = 0;

	private static long generate() {
		Date current = new Date();
		if (DateUtils.isSameDay(current, BUILD_DATE)) {
			CURRENT_NUMBER++;
		} else {
			CURRENT_NUMBER = 1;
			BUILD_DATE = current;
		}
		return CURRENT_NUMBER;
	}

	private static String format(long serialNumber) {
		return String.format("%05d", serialNumber);
	}

	public static String generate(String prefix, String timeStamp) {
		StringBuilder apTransId = new StringBuilder(prefix);
		apTransId.append(timeStamp.substring(0, "yyyyMMdd".length()));
		apTransId.append(format(generate()));
		return apTransId.toString();
	}
}
