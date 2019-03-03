package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.NoticeSpecifications.findNotices;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.NoticeDao;
import com.yunforge.base.dao.NoticeDivisionDao;
import com.yunforge.base.dao.impl.NoticeDaoImpl;
import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;
import com.yunforge.base.service.NoticeManager;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.StringUtil;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class NoticeManagerImpl implements NoticeManager {

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private NoticeDivisionDao noticeDivisionDao;
	
	@Autowired
	private NoticeDaoImpl noticeDaoImpl;
	
	public List<Notice> getUnReadNoticeByCurrentDivCode(String divCode,int rowCount) {
		
//		noticeDao.updateNotice("呆呆党草泥马sdd", new Date(), "呆呆党草泥马s");
//		noticeDao.deleteNotice("as", "450000");
		
//		List list = noticeDao.findByNoticeDivisionList();
//		Pageable pageable = new PageRequest(0, 10);
//		Page<Notice> notices = noticeDao.findAll(findNotices(divCode),pageable);
//		
//		List<Notice> list1 = noticeDivisionDao.findNoticeByDivisionDivCodeAndNoticeStatusAndReadFlag(divCode, "1", "0", pageable);
		
//		return noticeDaoImpl.getUnReadNoticeByCurrentDivCode(divCode, rowCount);
		
		Pageable pageable = null;
		if(rowCount != -1) {
			pageable = new PageRequest(0, rowCount);
		}
		List list = noticeDao.getUnReadNoticeByCurrentDivCode(divCode, pageable);
		return list;
	}
	
	public List<Notice> getNoticeByCurrentDivCode(String divCode,int rowCount) {

		Pageable pageable = new PageRequest(0, rowCount);
		List list = noticeDao.getNoticeByCurrentDivCode(divCode, pageable);
//		List list = noticeDaoImpl.getNoticeByCurrentDivCode(divCode, rowCount);
//		List list1 = noticeDivisionDao.findNoticeDivisionNoticeByNoticeDivcodeAndNoticeStatusOrderByNoticeCreatedateAsc(divCode, "1",pageable);
		return list;
	}
	
	public void updateNotice(Notice notice) {
		/*sql.append(" update notice ");
		sql.append(" set title = ?1, ");
		sql.append(" 	ntcontent = ?2, ");
		sql.append(" 	modifydate = ?3, ");
		sql.append(" 	status = ?4 ");
		sql.append(" where id = ?5 ");*/
		Notice n = noticeDao.findOne(notice.getId());
		n.setTitle(notice.getTitle());
		n.setNtcontent(notice.getNtcontent());
		n.setModifydate(notice.getModifydate());
		n.setStatus(notice.getStatus());
		noticeDao.save(n);
//		noticeDaoImpl.updateNotice(notice);
	}

	public GridBean listNotices(Params params) {
		return noticeDaoImpl.listNotices(params);
	}
	
	public List<NoticeDivision> getNoticeDivisionByNoticeId(String noticeId) {
		return noticeDivisionDao.findByNoticeId(noticeId);
//		return noticeDaoImpl.getNoticeDivisionByNoticeId(noticeId);
	}
	
	public void deleteNoticeDivisionByNotice(String noticeId) {
		noticeDivisionDao.deleteByNoticeId(noticeId);
//		noticeDaoImpl.deleteNoticeDivisionByNoticeId(noticeId);
	}
	
	public void saveNoticeDivision(NoticeDivision nd) {
		noticeDivisionDao.saveAndFlush(nd);
	}

	public void setViewFlag(String divCode,String noticeId) {
		
		noticeDao.setViewFlag(divCode,noticeId);
//		noticeDaoImpl.setViewFlag(divCode,noticeId);
	}
	
	public GridBean queryViewList(Params params) {
		return noticeDaoImpl.queryViewList(params);
	}
	
	//
	public GridBean listNotices(String searchField, String searchOper, String searchString, Pageable pageable,Integer page,String divcode) {

		Page<Notice> notices = noticeDao.findAll(findNotices(searchField, searchOper, searchString,divcode),pageable);
		
		//blob无法显示
		for(int i = 0;i < notices.getContent().size();i++) {
//			notices.getContent().get(i).setContent(null);
		}
		
		return StringUtil.pageToGrid(notices, page);
	}
	
	public void saveNotice(Notice notice) {
//		noticeDao.save(notice);
		noticeDao.saveAndFlush(notice);
	}
	
	public void deleteNotice(String id) {
		noticeDao.delete(id);
		
		noticeDivisionDao.deleteByNoticeId(id);
//		noticeDaoImpl.deleteNoticeDivisionByNoticeId(id);
	}
	
	public Notice getNotice(String id) {
		return noticeDao.getOne(id);
	}
}
