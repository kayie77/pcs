package com.yunforge.core.web.listerner;

import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yunforge.common.util.YunforgeUtils;

public class SessionListener implements HttpSessionListener {

	private static Log log = LogFactory.getLog(SessionListener.class);

	static Hashtable<String, HttpSession> ht = new Hashtable<String, HttpSession>();

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ht.remove(session.getId());
		log.info("销毁了一个Session连接:" + session.getId() + " "
				+ YunforgeUtils.getCurrentTime());
	}

	static public void addSession(HttpSession session) {
		ht.put(session.getId(), session);
	}

	static public Iterator<HttpSession> getSessions() {
		return ht.values().iterator();
	}

	static public HttpSession getSessionByID(String sessionId) {
		return ht.get(sessionId);
	}
}
