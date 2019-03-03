package com.yunforge.core.web.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.yunforge.base.model.Parameter;
import com.yunforge.base.service.ParameterManager;
import com.yunforge.common.Constants;
import com.yunforge.common.util.YunforgeUtils;

@WebServlet(name = "dataInitializeServlet", urlPatterns = { "/dataInitializeServlet" }, description = "加载并初始化系统参数", loadOnStartup = 1)
public class DataInitializeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Log log = LogFactory.getLog(DataInitializeServlet.class);

	@Autowired
	private ParameterManager parameterManager;

	public DataInitializeServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// PropertiesHelper pHelper
		// =PropertiesFactory.getPropertiesHelper(PropertiesFile.PLATFORM);
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
		long start = System.currentTimeMillis();
		long timeSec = 0L;

		log.info("*******************************************************");
		log.info("云锋应用开发平台[Yunforge EAIP]开始启动...");
		log.info("*******************************************************");

		try {
			log.info("系统开始启动全局参数表装载程序...");
			log.info("开始加载全局参数表...");

			List<Parameter> paramList = parameterManager.findAll();
			this.getServletContext().setAttribute(Constants.SYS_PARA, paramList);
			timeSec = (System.currentTimeMillis() - start) / 1000;
			log.info("********************************************");
			log.info("云锋应用开发平台[Yunforge EAIP]启动成功["
					+ YunforgeUtils.getCurrentTime() + "]");
			log.info("启动总耗时: " + timeSec / 60 + "分 " + timeSec % 60 + "秒 ");
			log.info("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("云锋应用开发平台[Yunforge EAIP]启动失败["
					+ YunforgeUtils.getCurrentTime() + "]");
			log.error("启动总耗时: " + timeSec / 60 + "分" + timeSec % 60 + "秒");
		}
	}

}
