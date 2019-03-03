package com.yunforge.cms.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.core.model.IEntity;
import com.yunforge.core.web.springmvc.JsonDateSerializer;

@Entity
@Table(name = "CMS_INFO")
public class Info implements IEntity<String> {
	private static final long serialVersionUID = 2532667852623752037L;

	public final static Integer INFO_TYPE_INFO = 0; // 新闻
	public final static Integer INFO_TYPE_REPORT = 1; // 报告
	public final static Integer INFO_TYPE_QUESTION = 2; // 问答
	

	public final static Integer STATUS_DRAFT = 0;
	public final static Integer STATUS_AUDIT = 1;
	public final static Integer STATUS_ACCEPTED = 2;
	public final static Integer STATUS_REJECTED = 3;

	public final static int CATALOG_ALL = 0;
	public final static int CATALOG_WEEK = 1;
	public final static int CATALOG_MONTH = 2;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 36)
	private String id;

	private String title;

	@Column(name = "AUTHOR_ID")
	private String authorId;

	@JsonSerialize(using=JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "PUB_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pubDate;

	@JsonSerialize(using=JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "EXPIRED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDate;

	@Column(name = "APPROVER_ID")
	private String approverId;

	private String author;

	private String origin;

	@Embedded
	private InfoType infoType;

	private Integer status;

	private String ip;

	private Integer favorite;

	private Integer counter;

	@Column(name = "COMMENT_COUNT")
	private Integer commentCount;

	private BigDecimal score;

	@Column(name = "NOTIFY_PUB")
	private boolean notifyPub;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String body;

	private String summary;

	@Column(name = "URL")
	private String url;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "CAT_ID", nullable = false)
	private InfoCat infoCat;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "info", orphanRemoval = true)
	private Set<InfoFile> infoFiles = new HashSet<InfoFile>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "article", orphanRemoval = true)
	private Set<Rating> ratings = new HashSet<Rating>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "article", orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>(0);

	@JsonSerialize(using=JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	private boolean deleted;

	private Integer weight;

	public Info() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public InfoType getInfoType() {
		return infoType;
	}

	public void setInfoType(InfoType infoType) {
		this.infoType = infoType;
	}

	public String getApproverId() {
		return this.approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getStatus() {
		return this.status;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public boolean isNotifyPub() {
		return notifyPub;
	}

	public void setNotifyPub(boolean notifyPub) {
		this.notifyPub = notifyPub;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public InfoCat getInfoCat() {
		return infoCat;
	}

	public void setInfoCat(InfoCat infoCat) {
		this.infoCat = infoCat;
	}

	public Set<InfoFile> getInfoFiles() {
		return infoFiles;
	}

	public void setInfoFiles(Set<InfoFile> infoFiles) {
		this.infoFiles = infoFiles;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean ited) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Info [id=" + this.id + "]";
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
		Info other = (Info) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

}