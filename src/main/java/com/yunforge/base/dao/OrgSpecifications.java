package com.yunforge.base.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Org;
import com.yunforge.base.model.Org_;
import com.yunforge.base.model.Role_;

public class OrgSpecifications {

	public static Specification<Org> findOrgs(final String pid,
			final String filters) {

		return new Specification<Org>() {
			@Override
			public Predicate toPredicate(Root<Org> orgRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (pid != null && !"0".equals(pid)) {
					conditions = cb.equal(
							orgRoot.join(Org_.parent, JoinType.LEFT).get(
									Org_.id), pid);
				} else {
					conditions = cb.isNotNull(orgRoot.get(Org_.id));
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
						if (field.equals("orgName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										orgRoot.get(Org_.orgName), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										orgRoot.get(Org_.orgName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgName), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgName), "%" + data
												+ "%");
							}
						} else if (field.equals("orgCode")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										orgRoot.get(Org_.orgCode), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										orgRoot.get(Org_.orgCode), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgCode), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgCode), "%" + data
												+ "%");
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
	
	public static Specification<Org> findRoleOrgs(final String roleId,
			final String filters) {

		return new Specification<Org>() {
			@Override
			public Predicate toPredicate(Root<Org> orgRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (roleId != null && !"0".equals(roleId)) {
					conditions = cb.equal(
							orgRoot.join(Org_.roles, JoinType.LEFT).get(
									Role_.id), roleId);
				} else {
					conditions = cb.isNull(orgRoot.get(Org_.id));
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
						if (field.equals("orgName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										orgRoot.get(Org_.orgName), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										orgRoot.get(Org_.orgName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgName), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgName), "%" + data
												+ "%");
							}
						} else if (field.equals("orgCode")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										orgRoot.get(Org_.orgCode), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										orgRoot.get(Org_.orgCode), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgCode), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										orgRoot.get(Org_.orgCode), "%" + data
												+ "%");
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
