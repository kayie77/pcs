package com.yunforge.common;

/** 常量表 */
public class Constants {
	
	/** http端口属性 */
	public static final String HTTP_PORT = "httpPort";

	/** https SSL端口属性 */
	public static final String HTTPS_PORT = "httpsPort";

	/** 安全登录属性 */
	public static final String SECURE_LOGIN = "secureLogin";

	/** 密码算法属性 */
	public static final String ENC_ALGORITHM = "algorithm";

	/** 加密密码属性 */
	public static final String ENCRYPT_PASSWORD = "encryptPassword";

	/** 登录cookie属性 */
	public static final String LOGIN_COOKIE = "sessionId";

	/** 会话存储用户信息变量 */
	public static final String SESSION_USER_KEY = "userSession";

	public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";

	public static final String FAILURE_KEY = "shiroLoginFailure";
	
	public static final String ROLE_ADMIN_KEY = "ROLE_ADMIN";

	/**
	 * 格式化(24小时制)<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 格式化(12小时制)<br>
	 * FORMAT_DateTime: 日期时间
	 */
	public static final String FORMAT_DateTime_12 = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 格式化<br>
	 * FORMAT_DateTime: 日期
	 */
	public static final String FORMAT_Date = "yyyy-MM-dd";

	/**
	 * 格式化(24小时制)<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time = "HH:mm:ss";

	/**
	 * 格式化(12小时制)<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_Time_12 = "hh:mm:ss";

	
	public static final String DEFAULT_CONTEXT = "classpath*:spring/*.xml";

	public static final String DEFAULT_TEST_CONTEXT = "classpath*:spring/test/*.xml";

	public static final String FILE_SEP = System.getProperty("file.separator");

	public static final String USER_HOME = System.getProperty("user.home")
			+ FILE_SEP;

	/** 主题 */
	public static final String THEME = "theme";

	/** 布局颜色 */
	public static final String LAYOUT_COLOR = "layoutColor";

	/** 是否全屏 */
	public static final String FULL_SCREEN = "fullScreen";

	/** 列表每页显示行数 */
	public static final String PAGE_SIZE = "pageSize";
	
	/** 系统配置参数 */
	public static final String SYS_PARA = "sysPara";

	/** 开启页面提醒功能 */
	public static final String WEB_NOTIFIED = "webNotified";

	/** 开启短信提醒功能 */
	public static final String SMS_NOTIFIED = "smsNotified";

	/** 开启邮件提醒功能 */
	public static final String MAIL_NOTIFIED = "mailNotified";

	/** 开启即时通讯提醒功能 */
	public static final String IM_NOTIFIED = "imNotified";

	public static String ROOTPATH = "";

	public static String ClASSPATH = "";

	public static String LIBPATH = "";

	public static boolean USE_CACHE = true;

	public static final String JSON_VIEW = "jsonView";

	public static final String STRING_VIEW = "stringView";

	public static final String WORD_VIEW = "wordView";

	public static final String EXCEL_VIEW = "excelView";

	public static final String PDF_VIEW = "pdfView";

	public static final String XML_VIEW = "xmlView";

	public static final String IMAGE_VIEW = "imageView";

	public static final String ATTACHMENT_VIEW = "attachmentView";

}