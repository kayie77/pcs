package com.yunforge.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.cms.model.InfoType;
import com.yunforge.core.web.springmvc.JsonDateSerializer;

/**
 * @ClassName: InfoDto
 * @Description: 新闻、问答、报告Dto类
 * @author Oliver Wen
 * @date 2015年10月4日 下午8:18:51
 * 
 */
@JsonRootName("info")
public class InfoDto implements Serializable {
	private static final long serialVersionUID = 6646477777180612258L;

	public final static int INFOSTYPE_INFO = 0x00;// 0 资讯
	public final static int INFOSTYPE_QUESTION = 0x01;// 1问答
	public final static int INFOSTYPE_REPORT = 0x02;// 2 报告

	private String id;

	private String title;

	private String authorId;

	private String author;

	private String origin;

	private Date pubDate;

	private String summary;

	private String body;

	private String url;

	private InfoType infoType;

	private Integer favorite;

	private Integer counter;

	private Integer commentCount;

	private BigDecimal score;

	private List<RelativeDto> relatives = new ArrayList<RelativeDto>();


	public InfoDto() {
	
	}

	/**
	 * @param id
	 * @param title
	 * @param authorId
	 * @param author
	 * @param origin
	 * @param pubDate
	 * @param summary
	 * @param url
	 * @param infoType
	 * @param favorite
	 * @param counter
	 * @param commentCount
	 * @param score
	 */
	public InfoDto(String id, String title, String authorId, String author,
			String origin, Date pubDate, String summary, String url,
			InfoType infoType, Integer favorite, Integer counter,
			Integer commentCount, BigDecimal score) {

		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.author = author;
		this.origin = origin;
		this.pubDate = pubDate;
		this.summary = summary;
		this.url = url;
		this.infoType = infoType;
		this.favorite = favorite;
		this.counter = counter;
		this.commentCount = commentCount;
		this.score = score;
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

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public InfoType getInfoType() {
		return infoType;
	}

	public void setInfoType(InfoType infoType) {
		this.infoType = infoType;
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

	public List<RelativeDto> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<RelativeDto> relatives) {
		this.relatives = relatives;
	}

	@Override
	public String toString() {
		return "Info [title=" + title + "]";
	}

}
