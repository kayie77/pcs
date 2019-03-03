package com.yunforge.core.web.springmvc;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 数据绑定初始化类
 * 
 * @author Oliver Wen
 * 
 */
public class BindingInitializer implements WebBindingInitializer {
	/**
	 * 初始化数据绑定
	 */

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());
		binder.registerCustomEditor(double.class, new DoubleTypeEditor());
		binder.registerCustomEditor(double.class, new FloatTypeEditor());
		binder.registerCustomEditor(double.class, new IntegerTypeEditor());
		binder.registerCustomEditor(double.class, new LongTypeEditor());
	}
}
