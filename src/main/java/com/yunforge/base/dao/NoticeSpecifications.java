package com.yunforge.base.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Division_;
import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;
import com.yunforge.base.model.NoticeDivision_;
import com.yunforge.base.model.Notice_;
import com.yunforge.common.util.YunforgeUtils;

public class NoticeSpecifications {

	public static Specification<Notice> findNotices(final String searchField) {

		return new Specification<Notice>() {
			public Predicate toPredicate(Root<Notice> noticeRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
//				NoticeRoot.join(Notice_.noticeDivisionList,JoinType.LEFT);

//				Root<Notice> noticeRoot = query.from(Notice.class);
//				Root<NoticeDivision> noticeDivisionRoot = query.from(NoticeDivision.class);

				List divcodeArray = new ArrayList();
				divcodeArray.add("450122");
				divcodeArray.add("450000");
				
				ListJoin<Notice, NoticeDivision> noticeJoin = noticeRoot.join(Notice_.noticeDivisionList,JoinType.LEFT);
				Join<NoticeDivision, Division> noticeDivisionJoin = noticeJoin.join(NoticeDivision_.division,JoinType.LEFT);
				
				
//				noticeDivisionJoin.get(Division_.divCode).
//				p = cb.equal(, searchField);
//				p = cb.like(noticeRoot.get(Notice_.divcode),"%%");
//				p = cb.and(noticeRoot.get(Notice_.divcode),"");


//				Predicate p1 = noticeRoot.isNotNull();
//				Predicate p2 = cb.equal(noticeDivisionJoin.get(Division_.divCode), searchField);
//				Predicate p3 = cb.like(noticeRoot.get(Notice_.divcode),"%%");
//				Predicate p4 = noticeRoot.get(Notice_.divcode).in(divcodeArray);
//				query.orderBy(cb.asc(noticeRoot.get(Notice_.createdate)),cb.asc(noticeDivisionJoin.get(Division_.divCode)));
//				return query.where(p1,p2,p3,p4).getRestriction();
				
				
				
				
				Predicate p1 = noticeRoot.isNotNull();
				Predicate p4 = noticeRoot.get(Notice_.divcode).in(divcodeArray);
				Predicate p2 = cb.equal(noticeDivisionJoin.get(Division_.divCode), searchField);
				Predicate p3 = cb.like(noticeRoot.get(Notice_.divcode),"%%");
				query.orderBy(cb.asc(noticeRoot.get(Notice_.createdate)),cb.asc(noticeDivisionJoin.get(Division_.divCode)));
				return query.where(p1,p2,p3,p4).getRestriction();
				
				
				
//				p = p.in(noticeRoot.get(Notice_.divcode).in(divcodeArray));
//				p = noticeRoot.get(Notice_.divcode).in(divcodeArray);
//				Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
				
				
//				noticeDivisionRoot.join(NoticeDivision_.division,JoinType.LEFT);
//				query.where(arg0);
//				Predicate p = NoticeRoot.isNotNull();
//				return cb.and(p1,p2,p3,p4);
//				cb.and(p1);
//				cb.and(p2);
//				cb.and(p3);
//				cb.and(p4);
				
//				cb.equal(NoticeDivision_., arg1);
//				Predicate conditions= cb.equal(NoticeRoot.join(Notice_.noticeDivisionList,JoinType.LEFT).get(Role_.id), roleId);
//				conditions = cb.equal(
//							userRoot.join(User_.roles, JoinType.LEFT).get(
//									Role_.id), roleId);
//				return p;
//				return null;
			}
		};
	}
	public static Specification<Notice> findNotices(final String searchField, final String searchOper,final String searchString,final String divcode) {

		return new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> NoticeRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {

				String searchString1 = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				
//				Predicate conditions = null;
				Predicate conditions = cb.isNotNull(NoticeRoot.get(Notice_.id));
				if (searchField != null) {
					if (searchField.equals("title")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									NoticeRoot.get(Notice_.title),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									NoticeRoot.get(Notice_.title),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									NoticeRoot.get(Notice_.title),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									NoticeRoot.get(Notice_.title), "%" + searchString1 + "%"));
						}

					} else if (searchField.equals("status")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									NoticeRoot.get(Notice_.status),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									NoticeRoot.get(Notice_.status),
									searchString1));
						}

					} 
				}
				
				conditions = cb.and(conditions, cb.equal(
						NoticeRoot.get(Notice_.divcode),
						divcode));
				
				return conditions;
			}
		};
	}
	
}
