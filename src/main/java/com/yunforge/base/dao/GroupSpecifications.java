package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Group;
import com.yunforge.base.model.Group_;
import com.yunforge.base.model.Role_;
import com.yunforge.common.util.YunforgeUtils;

public class GroupSpecifications {

	public static Specification<Group> searchGroups(final String searchField,
			final String searchOper, final String searchString) {

		return new Specification<Group>() {
			@Override
			public Predicate toPredicate(Root<Group> groupRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(groupRoot.get(Group_.id));
				String searchString1 = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				
				if (searchField != null) {
					if (searchField.equals("groupName")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									groupRoot.get(Group_.groupName), searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									groupRoot.get(Group_.groupName), searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.groupName), searchString1
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(groupRoot.get(Group_.groupName), "%"
											+ searchString1 + "%"));
						}

					} else if (searchField.equals("groupDesc")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									groupRoot.get(Group_.groupDesc),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									groupRoot.get(Group_.groupDesc),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.groupDesc),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.groupDesc), "%"
											+ searchString1 + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}

	public static Specification<Group> searchRoleGroups(final String roleId,
			final String searchField, final String searchOper,
			final String searchString) {

		return new Specification<Group>() {
			@Override
			public Predicate toPredicate(Root<Group> groupRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (roleId != null && !"0".equals(roleId)) {
					conditions = cb.equal(
							groupRoot.join(Group_.roles, JoinType.LEFT).get(
									Role_.id), roleId);
				} else {
					conditions = cb.isNull(groupRoot.get(Group_.id));
				}
				if (searchField != null) {
					if (searchField.equals("id")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									groupRoot.get(Group_.id), searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									groupRoot.get(Group_.id), searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.id), searchString
											+ "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(
									conditions,
									cb.like(groupRoot.get(Group_.id), "%"
											+ searchString + "%"));
						}

					} else if (searchField.equals("groupName")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									groupRoot.get(Group_.groupName),
									searchString));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									groupRoot.get(Group_.groupName),
									searchString));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.groupName),
									searchString + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									groupRoot.get(Group_.groupName), "%"
											+ searchString + "%"));
						}

					}
				}
				return conditions;
			}
		};
	}
}
