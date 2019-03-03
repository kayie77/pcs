/**   
 * @Title: InfoDaoImpl.java 
 * @Package com.yunforge.cms.dao.impl 
 * @Description: TODO 
 * @author Oliver Wen  
 * @date 2015年10月9日 上午10:32:27 
 * @version V1.0   
 */
package com.yunforge.cms.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.yunforge.api.dto.InfoDto;
import com.yunforge.cms.dao.InfoDaoCustom;
import com.yunforge.cms.model.Info;
import com.yunforge.common.util.DateUtil;

/**
 * @ClassName: InfoDaoImpl
 * @Description: TODO
 * @author Oliver Wen
 * @date 2015年10月9日 上午10:32:27
 * 
 */
public class InfoDaoImpl implements InfoDaoCustom {

	@PersistenceContext
	public EntityManager em;

	/**
	 * @param status
	 * @param type
	 * @param catalog
	 * @param pageable
	 * @return queryByStatusAndTypeAndCatalog
	 */
	@Override
	public Page<InfoDto> queryByStatusAndTypeAndCatalog(Integer status,
			Integer type, Integer catalog, Pageable pageable) {
		TypedQuery<InfoDto> query = null;
		StringBuilder jpql = new StringBuilder(
				"SELECT NEW com.yunforge.api.dto.InfoDto(c.id, c.title, c.authorId, c.author, c.origin, c.pubDate, c.summary, c.url, c.infoType, c.favorite, c.counter, c.commentCount, c.score)");
		jpql.append(" FROM com.yunforge.cms.model.Info AS c");
		jpql.append(" WHERE c.status=?1");
		jpql.append(" AND c.infoType.type=?2");
		if (catalog != null && catalog != 0) {
			Date startDateTime = null;
			Date endDateTime = null;
			if (catalog == Info.CATALOG_WEEK) {
				startDateTime = DateUtil.getNowWeekBegin();
				endDateTime = DateUtil.getNowWeekEnd();

			} else if (catalog == Info.CATALOG_MONTH) {
				startDateTime = DateUtil.getMonthStartDate();
				endDateTime = DateUtil.getMonthEndDate();
			}

			jpql.append(" AND c.pubDate BETWEEN ?3 AND ?4");
			query = em.createQuery(jpql.toString(), InfoDto.class)
					.setParameter(1, status).setParameter(2, type)
					.setParameter(3, startDateTime, TemporalType.DATE)
					.setParameter(4, endDateTime, TemporalType.DATE);
		} else {
			query = em.createQuery(jpql.toString(), InfoDto.class)
					.setParameter(1, status).setParameter(2, type);
		}
		Integer total = query.getMaxResults();
		List<InfoDto> list = query.setFirstResult(pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();
		Page<InfoDto> page = new PageImpl<InfoDto>(list, pageable, total);

		return page;
	}

}
