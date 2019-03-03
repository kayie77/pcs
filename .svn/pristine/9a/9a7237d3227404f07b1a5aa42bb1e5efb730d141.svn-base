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
import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.MessageSend_;
import com.yunforge.base.model.Message_;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.YunforgeUtils;

public class MessageSpecifications {
	
	public static Specification<MessageSend> querySendedMessage(final Params params) {

		return new Specification<MessageSend>() {
			@Override
			public Predicate toPredicate(Root<MessageSend> messageSend, CriteriaQuery<?> query, CriteriaBuilder cb) {

				String title = (String)params.get("title");
				String status = (String)params.get("status");
				String div_name = (String)params.get("div_name");
				String divcode = (String)params.get("divcode");

				
//				Root<Message> message = query.from(Message.class);
//				Root<Division> divisionSd = query.from(Division.class);
//				Root<Division> divisionSdCreate = query.from(Division.class);

//				conditionSql.append(" and sd_create.div_code = '" +divcode+ "' ");
//				conditionSql.append(" and m.parentid is null ");
				
				Join<MessageSend,Message> join0 = messageSend.join(MessageSend_.message,JoinType.INNER);
				Join<MessageSend, Division> join = messageSend.join(MessageSend_.division,JoinType.INNER);
				Join<Message, Division> join1 = join0.join(Message_.createDiv,JoinType.INNER);
//				divisionSdCreate.join(Message_.createDiv,JoinType.INNER);
				
				List<Predicate> predicateList = new ArrayList<Predicate>();
//				Predicate p1 = cb.isNotNull(message.get(Message_.id));
//				Predicate p2 = cb.and(cb.equal(message.get(Message_.id), messageSend.get(MessageSend_.message)));
//				Predicate p3 = cb.and(cb.equal(divisionSd.get(Division_.id), messageSend.get(MessageSend_.division)));
//				Predicate p4 = cb.and(cb.equal(divisionSdCreate.get(Division_.id), message.get(Message_.createDiv)));
//				Predicate p5 = cb.and(cb.equal(divisionSdCreate.get(Division_.divCode), divcode));
//				Predicate p6 = cb.and(cb.isNull(message.get(Message_.parent)));
				Predicate p7 = null;
				Predicate p8 = null;
				Predicate p9 = null;
				Predicate p10 = null;
				
//				predicateList.add(p1);
//				predicateList.add(p2);
//				predicateList.add(p3);
//				predicateList.add(p4);
//				predicateList.add(p5);
//				predicateList.add(p6);
				if(StringUtil.notEmpty(divcode)) {

//					p10 = cb.like(join.get(Division_.divCode), divcode);
//					p10 = cb.and(cb.like(join.get(Division_.divCode), divcode));
//					p10 = cb.and(cb.like(join.get(Division_.divCode), divcode));
					p10 = cb.like(join.get(Division_.divCode), divcode);
					predicateList.add(p10);
				}
				if(StringUtil.notEmpty(title)) {
					p7 = cb.and(cb.like(join0.get(Message_.title), "%" + title + "%"));
					predicateList.add(p7);
				}
				if(StringUtil.notEmpty(div_name)) {
					p8 = cb.and(cb.like(join.get(Division_.divName), "%" + div_name + "%"));
					predicateList.add(p8);
				}
				if(StringUtil.notEmpty(status)) {
					if("1".equals(status)) {
						p9 = cb.and(cb.equal(messageSend.get(MessageSend_.readflag),"0"));
					}
					if("2".equals(status)) {
						p9 = cb.and(cb.equal(messageSend.get(MessageSend_.readflag),"1"));
					}
					if("3".equals(status)) {
						p9 = cb.and(cb.equal(messageSend.get(MessageSend_.replayflag),"1"));
					}
					if("4".equals(status)) {
						p9 = cb.and(cb.equal(join0.get(Message_.important),"1"));
					}
					if("5".equals(status)) {
						p9 = cb.and(cb.equal(join0.get(Message_.needreplay),"1"));
					}
					predicateList.add(p9);
				}

				query.where(predicateList.toArray(new Predicate[]{}));
//				query.orderBy(arg0);
				/*
				 sql.append(" select ms.id,ms.readflag,m.important,m.needreplay,ms.replayflag,sd.div_name,m.title,m.createdate ");
				sql.append(" from message m inner join messagesend ms on m.id = ms.messageid ");
				sql.append(" inner join sys_division sd on sd.id = ms.divisionid ");
				sql.append(" inner join sys_division sd_create on sd_create.id = m.createdivid ");
				sql.append(" where 1=1 ");
				
				conditionSql.append(" and sd_create.div_code = '" +divcode+ "' ");
				conditionSql.append(" and m.parentid is null ");
				
				}
				sql.append(conditionSql.toString());
				
				if(StringUtil.notEmpty(sidx)) {
					orderSql.append(" order by " + orderMap.get(sidx) + " " + sord);
				} else {
					orderSql.append(" order by m.createdate desc ");
				}
		

		*/
				
				return query.getRestriction();
			}
		};
	}
	
	public static Specification<Message> findMessages(final String divCode,final int rowCount) {

		return new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate conditions = cb.isNotNull(root.get(Message_.id));
				conditions = cb.and(conditions, cb.equal(root.get(Message_.createDiv),divCode));
				
				return conditions;
			}
		};
	}
	

	public static Specification<Message> findMessages(final String searchField, final String searchOper,final String searchString) {

		return new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> MessageRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {

				String searchString1 = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
				
//				Predicate conditions = null;
				Predicate conditions = cb.isNotNull(MessageRoot.get(Message_.id));
				if (searchField != null) {
					if (searchField.equals("title")) {
						if (searchOper.equals("eq")) {
							conditions = cb.and(conditions, cb.equal(
									MessageRoot.get(Message_.title),
									searchString1));
						} else if (searchOper.equals("ne")) {
							conditions = cb.and(conditions, cb.notEqual(
									MessageRoot.get(Message_.title),
									searchString1));
						} else if (searchOper.equals("bw")) {
							conditions = cb.and(conditions, cb.like(
									MessageRoot.get(Message_.title),
									searchString1 + "%"));
						} else if (searchOper.equals("cn")) {
							conditions = cb.and(conditions, cb.like(
									MessageRoot.get(Message_.title), "%" + searchString1 + "%"));
						}

					} 
					
//					else if (searchField.equals("status")) {
//						if (searchOper.equals("eq")) {
//							conditions = cb.and(conditions, cb.equal(
//									MessageRoot.get(Message_.status),
//									searchString1));
//						} else if (searchOper.equals("ne")) {
//							conditions = cb.and(conditions, cb.notEqual(
//									MessageRoot.get(Message_.status),
//									searchString1));
//						}
//
//					} 
				}
				
				return conditions;
			}
		};
	}
	
}
