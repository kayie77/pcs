package com.yunforge.core.context;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

public class FrameBeanFactory {
	private static ApplicationContext context;

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static Object getBean(Class<?> clazz) {
		return context.getBeansOfType(clazz);
	}

	public static Object getBean(Class<?> clazz, boolean includeNonSingletons,
			boolean allowEagerInit) {
		return BeanFactoryUtils.beanOfType(context, clazz,
				includeNonSingletons, allowEagerInit);
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		if (context == null)
			context = applicationContext;
	}
}
