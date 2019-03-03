package com.yunforge.common.bean;

import java.util.HashMap;
import java.util.Map;

public class Results {

private Map<String,Object> map = new HashMap<String,Object>();
	
	public void put(String key,Object value) {
		map.put(key, value);
	}
	
	public Object get(String key) {
		return map.get(key);
	}
}
