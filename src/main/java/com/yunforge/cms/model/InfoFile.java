package com.yunforge.cms.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "CMS_INFO_FILE")
public class InfoFile implements IEntity<String> {
	private static final long serialVersionUID = -2849768178957840400L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 36)
	private String id;

	@Column(name = "FILE_NAME", length = 100)
	private String fileName;

	@Column(name = "CONTENT_TYPE", length = 50)
	private String contentType;

	@Column(name = "DEPOSIT_TYPE")
	private Integer depositType;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 3072000)
	private byte[] content;

	@Column(name = "FILE_PATH")
	private String filePath;

	@Column(name = "FILE_DESC")
	private String fileDesc;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private boolean deleted;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "INFO_ID", nullable = false)
	private Info info;

	public InfoFile() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getDepositType() {
		return depositType;
	}

	public void setDepositType(Integer depositType) {
		this.depositType = depositType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "InfoFile [id=" + this.id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoFile other = (InfoFile) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

}