package com.yunforge.support.sql;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class NativeSearchFilter {
	public enum Operator {
		NEQ, EQ, LIKE, GT, LT, GTE, LTE
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public NativeSearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME_TYPE
	 */
	public static Map<String, NativeSearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, NativeSearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			String value = (String)entry.getValue();
			if (StringUtils.isBlank(value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.splitByWholeSeparator(key, "__");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0].toUpperCase());
			if (operator == Operator.LIKE) {
				value = "%" + value + "%";
			}
			// 创建searchFilter
			NativeSearchFilter filter = new NativeSearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
	
//	public static void main() {
//		String[] nam = StringUtils.split("1__@__#", "__");
//		
//	}
}
