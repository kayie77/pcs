package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;

public interface NoticeManager {
	
	public void updateNotice(Notice notice);
	
	public List<Notice> getUnReadNoticeByCurrentDivCode(String divCode,int rowCount);
	
	public List<Notice> getNoticeByCurrentDivCode(String divCode,int rowCount);
	
	//Page<Notice>
	public GridBean listNotices(String searchField, String searchOper,String searchString, Pageable pageable ,Integer page,String divcode);
	
	public GridBean listNotices(Params params); 
	
	public GridBean queryViewList(Params params); 
	
	public void saveNotice(Notice notice);
	
	public void deleteNotice(String id);
	
	public Notice getNotice(String id);
	
	public void setViewFlag(String divCode,String noticeId);
	
	public void deleteNoticeDivisionByNotice(String noticeId);
	
	public void saveNoticeDivision(NoticeDivision nd);
	
	public List<NoticeDivision> getNoticeDivisionByNoticeId(String noticeId);
}