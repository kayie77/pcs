package com.yunforge.base.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.MessageDao;
import com.yunforge.base.dao.MessageFileDao;
import com.yunforge.base.dao.MessageSendDao;
import com.yunforge.base.dao.MessageSpecifications;
import com.yunforge.base.dao.impl.MessageDaoImpl;
import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageDTO;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.service.MessageManager;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.StringUtil;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class MessageManagerImpl implements MessageManager {

	@Autowired
	private MessageDao MessageDao;

	@Autowired
	private MessageSendDao MessageSendDao;

	@Autowired
	private MessageFileDao MessageFileDao;
	
	@Autowired
	private MessageDaoImpl MessageDaoImpl;

	public List<MessageSend> getUnReadMessageByCurrentDivCode(String divCode,int rowCount) {

		Pageable pageable = null;
		if(rowCount != -1) {
			pageable = new PageRequest(0, rowCount);
		}
		
		List<MessageSend> list = MessageSendDao.findByReadflagAndDivisionDivCode(0,divCode,pageable);
		return list;
	}
	
	public List<MessageSend> getMessageByCurrentDivCode(String divCode,int rowCount) {
//		List<MessageSend> list1 = MessageSendDao.findMessageSendByDivisionDivCodeAndReadflagOrderByMessageId(divCode, "0");
//		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
//		return MessageDao.findAll(MessageSpecifications.findMessages(divCode), sort);
//		List<MessageSend> list2 =  MessageDaoImpl.getMessageByCurrentDivCode(divCode, rowCount);
//		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
		
		Pageable pageable = null;
		if(rowCount != -1) {
			pageable = new PageRequest(0, rowCount);
		}
		List<MessageSend> list = MessageSendDao.findMessageSendByDivisionDivCodeOrderByMessageCreatedateDesc(divCode,pageable);
		return list;
	}
	
	public Message getReplyMessage(String messageId,String messageSendId) {
		List<Message> list = MessageSendDao.getReplyMessage(messageId,messageSendId);
		if(list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	public GridBean queryRecvMessage(Params params) {
		return MessageDaoImpl.queryRecvMessage(params);
	}
	
	public GridBean queryRecvList(String searchField, String searchOper,String searchString, Pageable pageable ,Integer page) {
		
		Page<Message> notices = MessageDao.findAll(MessageSpecifications.findMessages(searchField, searchOper, searchString),pageable);
		
		//blob无法显示
		for(int i = 0;i < notices.getContent().size();i++) {
			notices.getContent().get(i).setContent(null);
		}
		
		return StringUtil.pageToGrid(notices, page);
	}
	
	public MessageFile getMessageFileById(String id) {
		return MessageFileDao.getOne(id);
	}
	
	public List<MessageFile> getMessageFile(String messageId) {
		return MessageFileDao.findByMessageId(messageId);
//		return MessageDaoImpl.getMessageFile(messageId);
	}
	
	public void deleteMessageSend(String id) {
		MessageSendDao.delete(id);
	}
	
	public void saveMessageFile(MessageFile messageFile) {
		MessageFileDao.saveAndFlush(messageFile);
	}

	public MessageSend getMessageSend(String id) {
		return MessageSendDao.findOne(id);
	}
	
	public void deleteMessageSendAndOther(String messageSendId) {
		
		MessageSend messageSend = MessageSendDao.findOne(messageSendId);
		String messageId = messageSend.getMessage().getId();
		
		MessageSendDao.delete(messageSendId);
		
		List<MessageSend> messageSendList = MessageSendDao.findByMessageId(messageId);
		if(messageSendList.size() == 0) {
			
			MessageDao.delete(messageId);
			
			MessageFileDao.deleteByMessageId(messageId);
		}
//		MessageDaoImpl.deleteMessageSendAndOther(messageId);
	}
	
	public void saveMessageSend(MessageSend messageSend) {
		MessageSendDao.saveAndFlush(messageSend);
	}
	
	public void saveMessage(Message message) {
		MessageDao.saveAndFlush(message);
	}
	
	public GridBean querySendedMessage(Params params) {
		return MessageDaoImpl.querySendedMessage(params);
	}
	
	public GridBean querySendedMessage1(Params params) {
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");
		
		if("title".equals(sidx)) {
			sidx = "message.title";
		}
		
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
		}
		
		Pageable pageable = new PageRequest(Integer.parseInt(pageIndex), pageSize, sort);
		/*
		 * public GridBean eightreportList(String searchField, String searchOper,String searchString, Pageable pageable ,Integer page,String divcode,String ethink_type) {
			Page<DicReport> notices = dicReportDao.findAll(DicReportSpecifications.findDicReports(searchField, searchOper, searchString,ethink_type),pageable);
			return StringUtil.pageToGrid(notices, page);
		}
		 * */
		//return auditReportDao.findAll(findAuditReports(roleIds,searchField, searchOper, searchString),pageable);
		Page<MessageSend> messages = MessageSendDao.findAll(MessageSpecifications.querySendedMessage(params),pageable);
		GridBean g = StringUtil.pageToGrid(messages, Integer.parseInt(pageIndex));
		List<MessageSend> messageList = g.getRows();
		
		/*List<MessageDTO> resultList = new ArrayList<MessageDTO>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			message.setId(StringUtil.getString(obs[col++]));
			message.setReadflag(StringUtil.getString(obs[col++]));
			message.setImportant(StringUtil.getString(obs[col++]));
			message.setNeedreplay(StringUtil.getString(obs[col++]));
			message.setReplayflag(StringUtil.getString(obs[col++]));
			message.setDiv_name(StringUtil.getString(obs[col++]));
			message.setTitle(StringUtil.getString(obs[col++]));
			message.setCreatedate(StringUtil.subStr(obs[col++],0,19));
			
			resultList.add(message);
		}*/
		List<MessageDTO> resultList = new ArrayList<MessageDTO>();
		for(int i = 0;i < messageList.size();i++) {

			MessageSend messageSend = messageList.get(i);
			MessageDTO messageDTO = new MessageDTO();
			
			messageDTO.setId(messageSend.getId());
			messageDTO.setReadflag(messageSend.getReadflag() + "");
			messageDTO.setImportant(messageSend.getMessage().getImportant() + "");
			messageDTO.setNeedreplay(messageSend.getMessage().getNeedreplay() + "");
			messageDTO.setReplayflag(messageSend.getReplayflag() + "");
			messageDTO.setDiv_name(messageSend.getMessage().getCreateDiv().getDivName());
			messageDTO.setTitle(messageSend.getMessage().getTitle());
			messageDTO.setCreatedate(messageSend.getMessage().getCreatedateStr());
			
			resultList.add(messageDTO);
		}
		g.setRows(resultList);
		return g;
//		return MessageDaoImpl.querySendedMessage(params);
	}
	
	public Message getMessage(String id) {
		return MessageDao.findOne(id);
	}

	public void deleteMessageSendByMessage(String id) {
		MessageSendDao.deleteByMessageId(id);
//		MessageDaoImpl.deleteMessageSendByMessage(id);
	}

	public void deleteMessageAndFile(String messageId) {
//		MessageDaoImpl.deleteMessageAndFile(messageId);
		MessageSendDao.updateStatus(1,messageId);
		
		MessageDao.delete(messageId);

		MessageFileDao.deleteByMessageId(messageId);
	}
}
