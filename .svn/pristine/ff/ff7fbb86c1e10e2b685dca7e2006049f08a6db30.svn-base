package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageSend;

public interface MessageSendDao extends JpaRepository<MessageSend, String>, JpaSpecificationExecutor<MessageSend> {

	public List<MessageSend> findMessageSendByDivisionDivCodeOrderByMessageCreatedateDesc(String divCode,Pageable pageable);
	

	//from MessageSend ms where ms.division.divCode = '" + divCode + "' and ms.readflag = 0 order by ms.message.createdate desc
	public List<MessageSend> findByReadflagAndDivisionDivCode(Integer readflag,String divCode,Pageable pageable);
	
	@Query(" select m_replay " + 
		   " from Message m_replay,MessageSend ms " +
		   " where m_replay.parent.id = ?1 " +
		   " and m_replay.createDiv.id = ms.division.id " +
		   " and ms.id = ?2 ")
	public List<Message> getReplyMessage(String messageId,String messageSendId);
	
	public void deleteByMessageId(String id);
	
	//select id from messagesend where messageid
	public List<MessageSend> findByMessageId(String messageId);
	
	@Modifying
	@Transactional
	@Query(" update MessageSend ms set ms.readflag = ?1 where ms.message.id in (select m.parent.id from Message m where m.id = ?2 ) ")
	public void updateStatus(Integer readflag,String messageId);
}
