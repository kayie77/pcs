package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.DictVal;
import com.yunforge.base.model.DictVal_;
import com.yunforge.base.model.Dict_;

public class DictValSpecifications {

	public static Specification<DictVal> findDictVals(final String dictId,
			final String searchField, final String searchOper,
			final String searchString) {

		return new Specification<DictVal>() {
			@Override
			public Predicate toPredicate(Root<DictVal> dictValRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (StringUtils.isNotBlank(dictId)) {
					conditions = cb.equal(
							dictValRoot.join(DictVal_.dict, JoinType.LEFT).get(
									Dict_.id), dictId);
				} else {
					conditions = cb.isNull(dictValRoot.get(DictVal_.id));
				}
				if (searchField != null) {
					if (searchField.equals("valCode")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									dictValRoot.get(DictVal_.valCode),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									dictValRoot.get(DictVal_.valCode),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									dictValRoot.get(DictVal_.valCode),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									dictValRoot.get(DictVal_.valCode), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("valName")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									dictValRoot.get(DictVal_.valName),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									dictValRoot.get(DictVal_.valName),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									dictValRoot.get(DictVal_.valName),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									dictValRoot.get(DictVal_.valName), "%"
											+ searchString + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}
}
