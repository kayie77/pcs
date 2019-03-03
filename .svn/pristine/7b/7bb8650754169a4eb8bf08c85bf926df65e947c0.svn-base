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

import com.yunforge.base.model.Group_;
import com.yunforge.base.model.Person_;
import com.yunforge.base.model.Role_;
import com.yunforge.base.model.User;
import com.yunforge.base.model.User_;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.YunforgeUtils;

public class UserSpecifications {

	public static Specification<User> searchUsers(final String filters,final String searchField,final String searchOper,final String searchString,final String userNameParam) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> userRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				String field = searchField;
				String op = searchOper;
				String data = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				String userNameParam1 = YunforgeUtils.getString(userNameParam, "iso-8859-1", "utf-8");

				Predicate conditions = cb.isNotNull(userRoot.get(User_.id));
				//userRoot.fetch(User_.person, JoinType.INNER);
//				if (StringUtils.isNotBlank(filters)) {
//					JSONObject jsonFilter = (JSONObject) JSONSerializer
//							.toJSON(filters);
//					String groupOp = jsonFilter.getString("groupOp");
//					Object rules_string = jsonFilter.get("rules");
//					JSONArray rules = (JSONArray) JSONSerializer
//							.toJSON(rules_string);

					Predicate searchCondition = null;
					Predicate searchConditions = null;
//					int rulesCount = JSONArray.getDimensions(rules)[0];

//					for (int i = 0; i < rulesCount; i++) {
//						JSONObject rule = rules.getJSONObject(i);
//						String field = rule.getString("field");
//						String op = rule.getString("op");
//						String data = rule.getString("data");
						if(StringUtil.notEmpty(userNameParam1)) {
							searchCondition = cb.like(
									userRoot.get(User_.username), "%"
											+ userNameParam1 + "%");
						}
						if ("username".equals(field)
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.username), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.username), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), "%"
												+ data + "%");
							}
						} else if ("person.persName".equals(field)
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.persName), data);

							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.persName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), "%" + data
												+ "%");
							}
						} else if ("person.mobile".equals(field)
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), "%" + data
												+ "%");
							}
						} else if ("person.email".equals(field)
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), "%" + data
												+ "%");
							}
						} else if ("caSn".equals(field)
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.caSn), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.caSn), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.caSn), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.caSn), "%"
												+ data + "%");
							}
						}
//						if (searchCondition != null) {
//							if (i == 0) {
//								searchConditions = searchCondition;
//							} else {
//								if (groupOp.toUpperCase().equals("AND")) {
//									searchConditions = cb.and(searchConditions,
//											searchCondition);
//								} else if (groupOp.toUpperCase().equals("OR")) {
//									searchConditions = cb.or(searchConditions,
//											searchCondition);
//								}
//							}
//						}
//					}

					if (searchCondition != null) {
						conditions = cb.and(conditions, searchCondition);
					}
//				}
				return conditions;
			}
		};
	}

	public static Specification<User> searchRoleUsers(final String roleId,
			final String filters) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> userRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (roleId != null && !"0".equals(roleId)) {
					conditions = cb.equal(
							userRoot.join(User_.roles, JoinType.LEFT).get(
									Role_.id), roleId);
				} else {
					conditions = cb.isNull(userRoot.get(User_.id));
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
						if (field.equals("username")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.username), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.username), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), "%"
												+ data + "%");
							}
						} else if (field.equals("fullName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.persName), data);

							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.persName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), "%" + data
												+ "%");
							}
						} else if (field.equals("mobile")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), "%" + data
												+ "%");
							}
						} else if (field.equals("email")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), "%" + data
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

	public static Specification<User> searchGroupUsers(final String groupId,
			final String filters) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> userRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = null;
				if (groupId != null && !"0".equals(groupId)) {
					conditions = cb.equal(
							userRoot.join(User_.groups, JoinType.LEFT).get(
									Group_.id), groupId);
				} else {
					conditions = cb.isNull(userRoot.get(User_.id));
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
						if (field.equals("username")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.username), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.username), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), data
												+ "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.username), "%"
												+ data + "%");
							}
						} else if (field.equals("fullName")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.persName), data);

							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.persName), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.persName), "%" + data
												+ "%");
							}
						} else if (field.equals("mobile")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.mobile), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.mobile), "%" + data
												+ "%");
							}
						} else if (field.equals("email")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										userRoot.get(User_.person).get(
												Person_.email), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										userRoot.get(User_.person).get(
												Person_.email), "%" + data
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
