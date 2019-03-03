package com.yunforge.cms.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.jpa.domain.Specification;

import com.yunforge.cms.model.Info;
import com.yunforge.cms.model.InfoCat_;
import com.yunforge.cms.model.InfoType_;
import com.yunforge.cms.model.Info_;
import com.yunforge.common.bean.JqGridSearchBean;
import com.yunforge.common.bean.JqGridSearchDetailBean;
import com.yunforge.common.util.DateUtil;
import com.yunforge.core.security.ShiroUser;

public class InfoSpecifications {

	public static Specification<Info> findInfos(final Integer type,
			final Integer catalog, final String catId, final Object[] status,
			final boolean isPrivate, final boolean isDeleted,
			final String filters) {

		return new Specification<Info>() {
			@Override
			public Predicate toPredicate(Root<Info> infoRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate conditions = cb.isNotNull(infoRoot.get(Info_.id));

				if (type != null) {
					conditions = cb.and(conditions,
							cb.equal(infoRoot.get(Info_.infoType).get(InfoType_.type), type));
				}

				if (catalog != null && catalog != 0) {
					Date startDateTime = null;
					Date endDateTime = null;
					if (catalog == Info.CATALOG_WEEK) {
						startDateTime = DateUtil.getNowWeekBegin();
						endDateTime = DateUtil.getNowWeekEnd();
						conditions = cb.and(conditions, cb.between(
								infoRoot.get(Info_.pubDate), startDateTime,
								endDateTime));

					} else if (catalog == Info.CATALOG_MONTH) {
						startDateTime = DateUtil.getMonthStartDate();
						endDateTime = DateUtil.getMonthEndDate();
						conditions = cb.and(conditions, cb.between(
								infoRoot.get(Info_.pubDate), startDateTime,
								endDateTime));
					}
				}

				if (StringUtils.isNotBlank(catId)) {
					conditions = cb.equal(
							infoRoot.join(Info_.infoCat, JoinType.LEFT).get(
									InfoCat_.id), catId);
				}

				if (status != null && status.length > 0) {
					conditions = cb.and(conditions, infoRoot.get(Info_.status)
							.in(status));
				}

				if (isPrivate) {
					String authorId = ((ShiroUser) SecurityUtils.getSubject()
							.getPrincipal()).getUid();
					conditions = cb.and(conditions,
							cb.equal(infoRoot.get(Info_.authorId), authorId));
				}

				conditions = cb.and(conditions,
						cb.equal(infoRoot.get(Info_.deleted), isDeleted));

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
						if (field.equals("title")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										infoRoot.get(Info_.title), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										infoRoot.get(Info_.title), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.title), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.title), "%" + data
												+ "%");
							}
						} else if (field.equals("author")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										infoRoot.get(Info_.author), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										infoRoot.get(Info_.author), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.author), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.author), "%" + data
												+ "%");
							}
						} else if (field.equals("origin")
								&& StringUtils.isNotBlank(data)) {
							if (op.equals("eq")) {
								searchCondition = cb.equal(
										infoRoot.get(Info_.origin), data);
							} else if (op.equals("ne")) {
								searchCondition = cb.notEqual(
										infoRoot.get(Info_.origin), data);
							} else if (op.equals("bw")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.origin), data + "%");
							} else if (op.equals("cn")) {
								searchCondition = cb.like(
										infoRoot.get(Info_.origin), "%" + data
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

	public static Specification<Info> findInfos(final Integer type,
			final Integer catalog, final String catId, final Object[] status,
			final boolean isPrivate, final boolean isDeleted,
			final JqGridSearchBean searchBean) {

		return new Specification<Info>() {
			@Override
			public Predicate toPredicate(Root<Info> infoRoot,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(infoRoot.get(Info_.id));

				if (type != null) {
					conditions = cb.and(conditions,
							cb.equal(infoRoot.get(Info_.infoType).get(InfoType_.type), type));
				}

				if (catalog != null && catalog != 0) {
					Date startDateTime = null;
					Date endDateTime = null;
					conditions = cb.and(conditions,
							cb.equal(infoRoot.get(Info_.infoType).get(InfoType_.type), type));
					if (catalog == 1) {
						startDateTime = DateUtil.getNowWeekBegin();
						endDateTime = DateUtil.getNowWeekEnd();
						conditions = cb.and(conditions, cb.between(
								infoRoot.get(Info_.pubDate), startDateTime,
								endDateTime));

					} else if (catalog == 2) {
						startDateTime = DateUtil.getMonthStartDate();
						endDateTime = DateUtil.getMonthEndDate();
						conditions = cb.and(conditions, cb.between(
								infoRoot.get(Info_.pubDate), startDateTime,
								endDateTime));
					}
				}

				if (StringUtils.isNotBlank(catId)) {
					conditions = cb.equal(
							infoRoot.join(Info_.infoCat, JoinType.LEFT).get(
									InfoCat_.id), catId);
				}

				if (status != null && status.length > 0) {
					conditions = cb.and(conditions, infoRoot.get(Info_.status)
							.in(status));
				}

				if (isPrivate) {
					String authorId = ((ShiroUser) SecurityUtils.getSubject()
							.getPrincipal()).getUid();
					conditions = cb.and(conditions,
							cb.equal(infoRoot.get(Info_.authorId), authorId));
				}

				conditions = cb.and(conditions,
						cb.equal(infoRoot.get(Info_.deleted), isDeleted));

				if (searchBean != null) {
					String searchField = searchBean.getSearchField();
					String searchOper = searchBean.getSearchOper();
					String searchString = searchBean.getSearchString();
					String groupOp = searchBean.getGroupOp();
					List<JqGridSearchDetailBean> searchDetailBeans = searchBean
							.getRules();
					if (searchField != null && searchOper != null) {

						if (searchField.equals("title")) {
							if (searchOper.equals("eq")) {
								conditions = cb.and(conditions,
										cb.equal(infoRoot.get(Info_.title),
												searchString));
							} else if (searchOper.equals("ne")) {
								conditions = cb.and(conditions,
										cb.notEqual(infoRoot.get(Info_.title),
												searchString));
							} else if (searchOper.equals("bw")) {
								conditions = cb.and(conditions, cb.like(
										infoRoot.get(Info_.title), searchString
												+ "%"));
							} else if (searchOper.equals("cn")) {
								conditions = cb.and(
										conditions,
										cb.like(infoRoot.get(Info_.title), "%"
												+ searchString + "%"));
							}

						} else if (searchField.equals("author")) {
							if (searchOper.equals("eq")) {
								conditions = cb.and(conditions, cb.equal(
										infoRoot.get(Info_.author),
										searchString));
							} else if (searchOper.equals("ne")) {
								conditions = cb.and(conditions, cb.notEqual(
										infoRoot.get(Info_.author),
										searchString));
							} else if (searchOper.equals("bw")) {
								conditions = cb.and(conditions, cb.like(
										infoRoot.get(Info_.author),
										searchString + "%"));
							} else if (searchOper.equals("cn")) {
								conditions = cb.and(
										conditions,
										cb.like(infoRoot.get(Info_.author), "%"
												+ searchString + "%"));
							}

						} else if (searchField.equals("infoCat")) {
							if (searchOper.equals("eq")) {
								conditions = cb.and(conditions,
										cb.equal(
												infoRoot.join(Info_.infoCat,
														JoinType.LEFT).get(
														InfoCat_.catName),
												searchString));
							} else if (searchOper.equals("ne")) {
								conditions = cb.and(conditions, cb
										.notEqual(
												infoRoot.join(Info_.infoCat,
														JoinType.LEFT).get(
														InfoCat_.catName),
												searchString));
							} else if (searchOper.equals("bw")) {
								conditions = cb.and(
										conditions,
										cb.like(infoRoot.join(Info_.infoCat,
												JoinType.LEFT).get(
												InfoCat_.catName), searchString
												+ "%"));
							} else if (searchOper.equals("cn")) {
								conditions = cb.and(
										conditions,
										cb.like(infoRoot.join(Info_.infoCat,
												JoinType.LEFT).get(
												InfoCat_.catName), "%"
												+ searchString + "%"));
							}

						}

					} else if (groupOp != null && searchDetailBeans != null) {
						Predicate searchCondition = null;
						Predicate searchConditions = null;
						for (JqGridSearchDetailBean searchDetailBean : searchDetailBeans) {
							String field = searchDetailBean.getField();
							String op = searchDetailBean.getOp();
							String data = searchDetailBean.getData();

							if (field.equals("title")
									&& StringUtils.isNotBlank(data)) {
								if (op.equals("eq")) {
									searchCondition = cb.equal(
											infoRoot.get(Info_.title), data);
								} else if (op.equals("ne")) {
									searchCondition = cb.notEqual(
											infoRoot.get(Info_.title), data);
								} else if (op.equals("bw")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.title), data
													+ "%");
								} else if (op.equals("cn")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.title), "%"
													+ data + "%");
								}
							} else if (field.equals("author")
									&& StringUtils.isNotBlank(data)) {
								if (op.equals("eq")) {
									searchCondition = cb.equal(
											infoRoot.get(Info_.author), data);
								} else if (op.equals("ne")) {
									searchCondition = cb.notEqual(
											infoRoot.get(Info_.author), data);
								} else if (op.equals("bw")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.author), data
													+ "%");
								} else if (op.equals("cn")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.author), "%"
													+ data + "%");
								}
							} else if (field.equals("origin")
									&& StringUtils.isNotBlank(data)) {
								if (op.equals("eq")) {
									searchCondition = cb.equal(
											infoRoot.get(Info_.origin), data);
								} else if (op.equals("ne")) {
									searchCondition = cb.notEqual(
											infoRoot.get(Info_.origin), data);
								} else if (op.equals("bw")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.origin), data
													+ "%");
								} else if (op.equals("cn")) {
									searchCondition = cb.like(
											infoRoot.get(Info_.origin), "%"
													+ data + "%");
								}
							}

							if (searchCondition != null) {
								if (searchConditions == null) {
									searchConditions = searchCondition;
								} else {
									if (groupOp.toUpperCase().equals("AND")) {
										searchConditions = cb.and(
												searchConditions,
												searchCondition);
									} else if (groupOp.toUpperCase().equals(
											"OR")) {
										searchConditions = cb.or(
												searchConditions,
												searchCondition);
									}
								}
							}
						}
						if (searchConditions != null) {
							conditions = cb.and(conditions, searchConditions);
						}
					}
				}
				return conditions;
			}
		};
	}

}
