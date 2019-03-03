package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Parameter;
import com.yunforge.base.model.Parameter_;
import com.yunforge.common.util.YunforgeUtils;

public class ParameterSpecifications {

	public static Specification<Parameter> searchConfigs(final String searchField,
			final String searchOper, final String searchString) {

		return new Specification<Parameter>() {
			@Override
			public Predicate toPredicate(Root<Parameter> paraRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String searchString1 = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				Predicate conditions = cb.isNotNull(paraRoot.get(Parameter_.id));
				if (searchField != null) {
					if (searchField.equals("paraCode")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									paraRoot.get(Parameter_.paraCode), searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									paraRoot.get(Parameter_.paraCode), searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraCode), searchString1
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(paraRoot.get(Parameter_.paraCode), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("paraName")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									paraRoot.get(Parameter_.paraName),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									paraRoot.get(Parameter_.paraName),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraName),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraName), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("paraVal")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									paraRoot.get(Parameter_.paraVal),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									paraRoot.get(Parameter_.paraVal),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraVal),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraVal), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("paraDesc")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									paraRoot.get(Parameter_.paraDesc),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									paraRoot.get(Parameter_.paraDesc),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraDesc),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									paraRoot.get(Parameter_.paraDesc), "%"
											+ searchString1 + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}
}
