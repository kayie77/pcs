package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.Notice;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;

public interface MessageManager {

	public List<MessageSend> getUnReadMessageByCurrentDivCode(String divCode,int rowCount);
	
	public List<MessageSend> getMessageByCurrentDivCode(String divCode,int rowCount);
	
	public GridBean queryRecvList(String searchField, String searchOper,String searchString, Pageable pageable ,Integer page);

	public GridBean queryRecvMessage(Params params);
	
	public GridBean querySendedMessage(Params params);
	
	public void saveMessage(Message message);
	
	public void saveMessageSend(MessageSend messageSend);
	
	public Message getMessage(String id);
	
	public Message getReplyMessage(String messageId,String messageSendId);
	
	public MessageSend getMessageSend(String id);

	public MessageFile getMessageFileById(String id);
	
	public List<MessageFile> getMessageFile(String messageId);
	
	public void deleteMessageSendByMessage(String id);
	
	public void deleteMessageSendAndOther(String messageId);
	
	public void deleteMessageAndFile(String messageId);

	public void deleteMessageSend(String id);
	
	public void saveMessageFile(MessageFile messageFile);
}