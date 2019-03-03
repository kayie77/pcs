package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;

public interface NoticeDivisionDao extends JpaRepository<NoticeDivision, String>,
		JpaSpecificationExecutor<NoticeDivision> {

	public List<Notice> findNoticeDivisionNoticeByNoticeDivcodeAndNoticeStatusOrderByNoticeCreatedateAsc(String divCode,String status,Pageable pageable);
	
	public List<Notice> findNoticeByDivisionDivCodeAndNoticeStatusAndReadFlag(String divcode,String status,String readflag,Pageable pageable);
	
	//from NoticeDivision nd where nd.notice.id = '" +noticeId+ "'"
	
	public List<NoticeDivision> findByNoticeId(String noticeId);
	
	public void deleteByNoticeId(String noticeId);
}
