package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Year;
import com.yunforge.base.model.Year_;

public class YearSpecifications {

	public static Specification<Year> searchYears(final String searchField,
			final String searchOper, final String searchString) {

		return new Specification<Year>() {
			@Override
			public Predicate toPredicate(Root<Year> yearRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(yearRoot.get(Year_.id));
				if (searchField != null) {
					if (searchField.equals("yearName")) {
						if (searchOper.equals("eq")) {
							conditions = cb
									.and(conditions, cb.equal(
											yearRoot.get(Year_.yearName),
											searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb
									.and(conditions, cb.notEqual(
											yearRoot.get(Year_.yearName),
											searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									yearRoot.get(Year_.yearName), searchString
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(yearRoot.get(Year_.yearName), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("yearNum")) {
						if (searchOper.equals("eq")) {
							conditions = cb
									.and(conditions, cb.equal(
											yearRoot.get(Year_.yearNum),
											searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb
									.and(conditions, cb.notEqual(
											yearRoot.get(Year_.yearNum),
											searchString));
						}

					}
				}
				return conditions;
			}
		};
	}
}
