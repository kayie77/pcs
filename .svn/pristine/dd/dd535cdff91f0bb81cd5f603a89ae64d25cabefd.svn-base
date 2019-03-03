package com.yunforge.collect.quartz;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections.map.HashedMap;

/**
 * JPA 初始化
 * @author heliteng
 *
 */
public final class JpaUtil {
	private static EntityManagerFactory emf;
	static {
		Map prop=new HashMap<String, String>();
		prop.put("javax.persistence.jdbc.user", "root");
		prop.put("javax.persistence.jdbc.password", "admin");
		prop.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/pcs");
		prop.put("javax.persistence.hbm2ddl.auto", "update");
		prop.put("javax.persistence.show_sql", "true");
		prop.put("javax.persistence.format_sql", "false");
		prop.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		prop.put("javax.persistence.dialect", "org.hibernate.dialect.MySQL5Dialect");
		emf = Persistence.createEntityManagerFactory("agrims",prop);
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
