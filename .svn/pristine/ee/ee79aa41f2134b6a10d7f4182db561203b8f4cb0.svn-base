package com.yunforge.base.model;

//import oracle.sql.BLOB;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.common.util.StringUtil;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "oper_MESSAGE")
public class Message implements IEntity<String> {
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE_FILE_PATH = "message";
	public static final String WORDTYPEFILE_FILE_PATH = "wordtypefile";
	public static final Integer STATUS_PUBLISH = 1;
	public static final Integer STATUS_UNPUBLISH = 2;
	public static final Integer STATUS_DELETE = 0;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "title")
	private String title;

//	private Blob content;
	@Column(name = "content")
	private Blob content;

	@Column(name = "important")
	private Integer important;
	
	@Column(name = "needreplay")
	private Integer needreplay;

	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "createdivid", referencedColumnName = "id", unique = true, nullable = false)
	private Division createDiv;
	
	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "modifydivid", referencedColumnName = "id", unique = true, nullable = false)
	private Division modifyDiv;
	
	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "parentid", referencedColumnName = "id", unique = true, nullable = false)
	private Message parent;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message", orphanRemoval = true)
	private List<MessageSend> messageSendList = new ArrayList<MessageSend>();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdate")
	private Date createdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydate")
	private Date modifydate;

	@Transient
	private String divIdStr;
	
	@Transient
	private String createdateStr;
	
	@Transient
	private String modifydateStr;
	
	@Transient
	private String contentStr;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public Integer getNeedreplay() {
		return needreplay;
	}

	public void setNeedreplay(Integer needreplay) {
		this.needreplay = needreplay;
	}

	public Division getCreateDiv() {
		return createDiv;
	}

	public void setCreateDiv(Division createDiv) {
		this.createDiv = createDiv;
	}

	public Division getModifyDiv() {
		return modifyDiv;
	}

	public void setModifyDiv(Division modifyDiv) {
		this.modifyDiv = modifyDiv;
	}

	public String getDivIdStr() {
		return divIdStr;
	}

	public void setDivIdStr(String divIdStr) {
		this.divIdStr = divIdStr;
	}

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	public String getCreatedateStr() {
		return StringUtil.getYMDHMS(createdate);
//		return createdateStr;
	}

	public void setCreatedateStr(String createdateStr) {
		this.createdateStr = createdateStr;
	}

	public String getModifydateStr() {
		return StringUtil.getYMDHMS(modifydate);
//		return modifydateStr;
	}

	public void setModifydateStr(String modifydateStr) {
		this.modifydateStr = modifydateStr;
	}

	public Message getParent() {
		return parent;
	}

	public void setParent(Message parent) {
		this.parent = parent;
	}

}