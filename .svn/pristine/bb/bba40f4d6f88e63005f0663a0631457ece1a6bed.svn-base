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

import com.yunforge.base.model.Org_;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.Person_;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.YunforgeUtils;

public class PersonSpecifications {

	public static Specification<Person> searchPersons(final String filters,final String persName) {

		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> personRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(personRoot.get(Person_.id));
				if(StringUtil.notEmpty(persName)) {
					String persName1 = YunforgeUtils.getString(persName, "iso-8859-1", "utf-8");
					conditions = cb.like(
							personRoot.get(Person_.persName), "%"
									+ persName1 + "%");
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
						if (field.equals("empName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										personRoot.get(Person_.persName),
										data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										personRoot.get(Person_.persName),
										data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										personRoot.get(Person_.persName),
										data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										personRoot.get(Person_.persName), "%"
												+ data + "%");
							}
						} else if (field.equals("empNum")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										personRoot.get(Person_.empNum), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										personRoot.get(Person_.empNum), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										personRoot.get(Person_.empNum), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										personRoot.get(Person_.empNum), "%"
												+ data + "%");
							}
						} else if (field.equals("mobile")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										personRoot.get(Person_.mobile), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										personRoot.get(Person_.mobile), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										personRoot.get(Person_.mobile), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										personRoot.get(Person_.mobile), "%"
												+ data + "%");
							}
						} else if (field.equals("email")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										personRoot.get(Person_.email), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										personRoot.get(Person_.email), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										personRoot.get(Person_.email), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										personRoot.get(Person_.email), "%"
												+ data + "%");
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

	public static Specification<Person> searchOrgPersons(final String orgId,
			final String filters) {

		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> employeeRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (orgId != null && !"0".equals(orgId)) {
					conditions = cb.equal(
							employeeRoot.join(Person_.org, JoinType.LEFT).get(
									Org_.id), orgId);
				} else {
					conditions = cb.isNull(employeeRoot.get(Person_.id));
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
						if (field.equals("empNum")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										employeeRoot.get(Person_.empNum), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										employeeRoot.get(Person_.empNum), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.empNum), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.empNum), "%"
												+ data + "%");
							}
						} else if (field.equals("mobile")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										employeeRoot.get(Person_.mobile), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										employeeRoot.get(Person_.mobile), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.mobile), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.mobile), "%"
												+ data + "%");
							}
						} else if (field.equals("email")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										employeeRoot.get(Person_.email), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										employeeRoot.get(Person_.email), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.email), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										employeeRoot.get(Person_.email), "%"
												+ data + "%");
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
