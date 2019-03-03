package com.yunforge.base.dao;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Resource_;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.Role_;

public class ResourceSpecifications {

	public static Specification<Resource> findResources(final String pid,
			final String resType, final Set<String> roleNames) {

		return new Specification<Resource>() {
			@Override
			public Predicate toPredicate(Root<Resource> resRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (StringUtils.isNotBlank(pid)) {
					conditions = cb.equal(
							resRoot.join(Resource_.parent, JoinType.LEFT).get(
									Resource_.id), pid);
				} else {
					conditions = cb.isNull(
							resRoot.join(Resource_.parent, JoinType.LEFT).get(
									Resource_.id));
				}
				if (StringUtils.isNotBlank(resType)) {
					conditions = cb.and(conditions,
							cb.equal(resRoot.get(Resource_.resType), resType));
				}

				if (roleNames != null && roleNames.size() > 0) {
					Join<Resource, Role> join = resRoot.join(Resource_.roles,
							JoinType.INNER);
					conditions = cb.and(conditions, join.get(Role_.roleName)
							.in(roleNames));
				}
				return conditions;
			}
		};
	}

	public static Specification<Resource> searchResources(final String pid,
			final String filters) {

		return new Specification<Resource>() {
			@Override
			public Predicate toPredicate(Root<Resource> resRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (pid != null && !"0".equals(pid)) {
					conditions = cb.equal(
							resRoot.join(Resource_.parent, JoinType.LEFT).get(
									Resource_.id), pid);
				} else {
					conditions = cb.isNotNull(resRoot.get(Resource_.id));
				}
				if (StringUtils.isNotBlank(filters)) {
					JSONObject jsonFilter = (JSONObject) JSONSerializer
							.toJSON(filters);
					String groupOp = jsonFilter.getString("groupOp");
					Object rules_string = jsonFilter.get("rules");
					JSONArray rules = (JSONArray) JSONSerializer
							.toJSON(rules_string);
					Predicate searchCondition = null;
					Predicate searchConditions = null;
					int rulesCount = JSONArray.getDimensions(rules)[0];
					for (int i = 0; i < rulesCount; i++) {
						JSONObject rule = rules.getJSONObject(i);
						String field = rule.getString("field");
						String op = rule.getString("op");
						String data = rule.getString("data");
						if (field.equals("resName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										resRoot.get(Resource_.resName), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										resRoot.get(Resource_.resName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										resRoot.get(Resource_.resName), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										resRoot.get(Resource_.resName), "%"
												+ data + "%");
							}
						} else if (field.equals("resType")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										resRoot.get(Resource_.resType), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										resRoot.get(Resource_.resType), data);
							}
						}
						if (searchCondition != null) {
							if (i == 0) {
								searchConditions = searchCondition;
							} else {
								if (groupOp.toUpperCase().equals("AND")) {
									searchConditions = cb.and(searchConditions,
											searchCondition);
								} else if (groupOp.toUpperCase().equals("OR")) {
									searchConditions = cb.or(searchConditions,
											searchCondition);
								}
							}
						}
					}

					if (searchConditions != null) {
						conditions = cb.and(conditions, searchConditions);
					}
				}

				return conditions;
			}
		};
	}

}
