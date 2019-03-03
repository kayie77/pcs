package com.yunforge.base.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;

public interface NoticeDao extends JpaRepository<Notice, String>,
		JpaSpecificationExecutor<Notice> {

//	public List<Notice> list(Map<String,Object> param);
	
	@Modifying
	@Transactional
	@Query("delete from Notice u where u.title = ?1 and u.divcode = ?2")
	public void deleteNotice(String title,String divcode);
	
	// and exists (select nd.notice.id NoticeDivision nd where nd.notice.id = n.id)
	
	@Modifying
	@Query("update Notice n set n.title = ?1,n.createdate = ?2 where n.title = ?3 and exists (select nd.notice.id from NoticeDivision nd where nd.notice.id = n.id)")
	public int updateNotice(String title, Date createDate,String title1);
	
	@Query(" select nd.notice "+
			" from NoticeDivision nd "+
			" where nd.division.divCode = ?1 "+
			" and nd.notice.status = '1' "+
			" and nd.readFlag = '0' "+
			" order by nd.notice.createdate ")
	public List<Notice> getUnReadNoticeByCurrentDivCode(String divCode,Pageable pageable);
	
	@Query(" select nd.notice " +
			" from NoticeDivision nd " +
			" where nd.division.divCode = ?1 " +
			" and nd.notice.status = '1' " +
			" order by nd.notice.createdate desc ")
	public List<Notice> getNoticeByCurrentDivCode(String divCode,Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value=" update noticedivision "+
					" set readflag = '1',readdate = sysdate "+
					" where noticeid = ?2 "+
					" and divisionid in ( "+
					" 	select id "+
					" 	from sys_division "+
					" 	where div_code = ?1 "+
					" ) ",nativeQuery = true)
	public void setViewFlag(String divCode,String noticeId);
//	public List<Notice> findByNoticeDivisionList();
	
//	@PersistenceContext
//	public EntityManager em;
	
//	public Collection loadProductsByCategory(String category) {
//		Query query = em.createQuery("from Product as p where p.category = :category");
//		query.setParameter("category", category);
//		return query.getResultList();
//	}
//	@Query("select e from SearchCountView e where e.userid=?1 and e.tender_id=?2 and e.type=?3 and e.state=1")
//    public List<Notice> getProductPseriesCountByEntpId(String entpid, String producttype, String productid);
}
