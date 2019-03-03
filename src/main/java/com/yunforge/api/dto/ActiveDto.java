package com.yunforge.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @ClassName: Active
 * @Description: 动态实体类
 * @author Oliver Wen
 * @date 2015年10月4日 上午2:50:32
 * 
 */
@JsonRootName("active")
public class ActiveDto implements Serializable {

	private static final long serialVersionUID = 322365018762741758L;
	public final static int CATALOG_OTHER = 0;// 其他
	public final static int CATALOG_INFO = 1;// 新闻
	public final static int CATALOG_QUESTION = 2;// 问题

	public final static int CLIENT_MOBILE = 2;
	public final static int CLIENT_ANDROID = 3;
	public final static int CLIENT_IPHONE = 4;
	public final static int CLIENT_WINDOWS_PHONE = 5;

	private String portrait;

	private String message;

	private String author;

	private String authorId;

	private int activeType;

	private String objectId;

	private int catalog;

	private int objectType;

	private int objectCatalog;

	private String objectTitle;

	private ObjectReply objectReply;

	private int commentCount;

	private String pubDate;

	private int appClient;

	private String url;

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public int getActiveType() {
		return activeType;
	}

	public void setActiveType(int activeType) {
		this.activeType = activeType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public int getObjectCatalog() {
		return objectCatalog;
	}

	public void setObjectCatalog(int objectCatalog) {
		this.objectCatalog = objectCatalog;
	}

	public String getObjectTitle() {
		return objectTitle;
	}

	public void setObjectTitle(String objectTitle) {
		this.objectTitle = objectTitle;
	}

	public ObjectReply getObjectReply() {
		return objectReply;
	}

	public void setObjectReply(ObjectReply objectReply) {
		this.objectReply = objectReply;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getAppClient() {
		return appClient;
	}

	public void setAppClient(int appClient) {
		this.appClient = appClient;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static class ObjectReply implements Serializable {
		private static final long serialVersionUID = 903819113239525489L;


		public String objectName;

	
		public String objectBody;

		public String getObjectName() {
			return objectName;
		}

		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}

		public String getObjectBody() {
			return objectBody;
		}

		public void setObjectBody(String objectBody) {
			this.objectBody = objectBody;
		}
	}
}
