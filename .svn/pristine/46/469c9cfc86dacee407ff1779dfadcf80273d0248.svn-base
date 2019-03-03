package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Resource_;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.Role_;
import com.yunforge.common.util.YunforgeUtils;

public class RoleSpecifications {

	public static Specification<Role> searchRoles(final String searchField,
			final String searchOper, final String searchString) {

		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> roleRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(roleRoot.get(Role_.id));
				String searchString1 = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				if (searchField != null) {
					if (searchField.equals("roleNameCN")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									roleRoot.get(Role_.roleNameCN), searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									roleRoot.get(Role_.roleNameCN), searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb
									.and(conditions, cb.like(
											roleRoot.get(Role_.roleNameCN),
											searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(roleRoot.get(Role_.roleNameCN), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("roleName")) {
						if (searchOper.equals("eq")) {
							conditions = cb
									.and(conditions, cb.equal(
											roleRoot.get(Role_.roleName),
											searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb
									.and(conditions, cb.notEqual(
											roleRoot.get(Role_.roleName),
											searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									roleRoot.get(Role_.roleName), searchString1
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(roleRoot.get(Role_.roleName), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("roleDesc")) {
						if (searchOper.equals("eq")) {
							conditions = cb
									.and(conditions, cb.equal(
											roleRoot.get(Role_.roleDesc),
											searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb
									.and(conditions, cb.notEqual(
											roleRoot.get(Role_.roleDesc),
											searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									roleRoot.get(Role_.roleDesc), searchString1
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(roleRoot.get(Role_.roleDesc), "%"
											+ searchString1 + "%"));
						}

					} 
				}
				return conditions;
			}
		};
	}

	public static Specification<Role> searchResourceRoles(final String resId,
			final String searchField, final String searchOper,
			final String searchString) {

		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> roleRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (resId != null && !"0".equals(resId)) {
					conditions = cb.equal(
							roleRoot.join(Role_.resources, JoinType.LEFT).get(
									Resource_.id), resId);
				} else {
					conditions = cb.isNull(roleRoot.get(Role_.id));
				}
				if (searchField != null) {
					if (searchField.equals("id")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									roleRoot.get(Role_.id), searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									roleRoot.get(Role_.id), searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb
									.and(conditions, cb.like(
											roleRoot.get(Role_.id),
											searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(roleRoot.get(Role_.id), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("roleName")) {
						if (searchOper.equals("eq")) {
							conditions = cb
									.and(conditions, cb.equal(
											roleRoot.get(Role_.roleName),
											searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb
									.and(conditions, cb.notEqual(
											roleRoot.get(Role_.roleName),
											searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									roleRoot.get(Role_.roleName), searchString
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(roleRoot.get(Role_.roleName), "%"
											+ searchString + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}
}
