package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.SysLog;
import com.yunforge.base.model.SysLog_;

public class SysLogSpecifications {

	public static Specification<SysLog> searchLogs(final String searchField,
			final String searchOper, final String searchString) {

		return new Specification<SysLog>() {
			@Override
			public Predicate toPredicate(Root<SysLog> logEventRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(logEventRoot
						.get(SysLog_.id));
				if (searchField != null) {
					if (searchField.equals("username")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									logEventRoot.get(SysLog_.username),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									logEventRoot.get(SysLog_.username),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.username),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.username), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("fullName")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									logEventRoot.get(SysLog_.fullName),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									logEventRoot.get(SysLog_.fullName),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.fullName),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.fullName), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("ipAddress")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									logEventRoot.get(SysLog_.ipAddress),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									logEventRoot.get(SysLog_.ipAddress),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.ipAddress),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									logEventRoot.get(SysLog_.ipAddress), "%"
											+ searchString + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}

}
