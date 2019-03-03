package com.yunforge.collect.quartz;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;

public class Task {

	public static void main(String[] args) {
		DataReoprtedMain task = new DataReoprtedMain();
		task.setId(UUID.randomUUID().toString());
		task.setTaskName("teskName001");
		task.setPeriod(1000);
		releaseTask(task);
	}

	// 发布任务
	public static void releaseTask(DataReoprtedMain task) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = JpaUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(task);
			tx.commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// 获取任务
	public static void getTask() {
		EntityManager em = null;
		Query query = em
				.createQuery("select p from Person p where p.personid=:Id ");
		query.setParameter("Id", new Integer(1));
		List result = query.getResultList();
		if (em != null) {
			em.close();
		}
	}
}
