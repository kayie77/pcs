package com.yunforge.base.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.common.util.DateUtil;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "oper_MESSAGESEND")
public class MessageSend implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "readflag")
	private Integer readflag;
	
	@Column(name = "replayflag")
	private Integer replayflag;

	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "messageid", referencedColumnName = "id", unique = true, nullable = false)
	private Message message;

	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "divisionid", referencedColumnName = "id", unique = true, nullable = false)
	private Division division;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageSend other = (MessageSend) obj;
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getReadflag() {
		return readflag;
	}

	public void setReadflag(Integer readflag) {
		this.readflag = readflag;
	}

	public Integer getReplayflag() {
		return replayflag;
	}

	public void setReplayflag(Integer replayflag) {
		this.replayflag = replayflag;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

}