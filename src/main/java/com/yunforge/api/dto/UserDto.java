package com.yunforge.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @ClassName: User
 * @Description: 登录用户实体类
 * @author Oliver Wen
 * @date 2015年10月5日 上午1:27:13
 * 
 */
@JsonRootName("user")
public class UserDto implements Serializable {
	private static final long serialVersionUID = -3556484201733914627L;
	public final static int RELATION_ACTION_DELETE = 0x00;// 取消关注
	public final static int RELATION_ACTION_ADD = 0x01;// 加关注

	public final static int RELATION_TYPE_BOTH = 0x01;// 双方互为粉丝
	public final static int RELATION_TYPE_FANS_HIM = 0x02;// 你单方面关注他
	public final static int RELATION_TYPE_NULL = 0x03;// 互不关注
	public final static int RELATION_TYPE_FANS_ME = 0x04;// 只有他关注我

	private String id;

	private String location;

	private String name;

	private int followers;

	private int fans;

	private int score;

	private String portrait;

	private String gender;
	private String personId;

	@JsonInclude(Include.NON_NULL)
	private String latestonline;

	@JsonInclude(Include.NON_NULL)
	private String from;

	private int favoritecount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLatestonline() {
		return latestonline;
	}

	public void setLatestonline(String latestonline) {
		this.latestonline = latestonline;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getFavoritecount() {
		return favoritecount;
	}

	public void setFavoritecount(int favoritecount) {
		this.favoritecount = favoritecount;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "User [uid=" + id + ", location=" + location + ", name=" + name
				+ ", followers=" + followers + ", fans=" + fans + ", score="
				+ score + ", portrait=" + portrait + ", gender=" + gender
				+ ", latestonline=" + latestonline + ", from=" + from
				+ ", favoritecount=" + favoritecount + ", isRememberMe=" + "]";
	}
}
