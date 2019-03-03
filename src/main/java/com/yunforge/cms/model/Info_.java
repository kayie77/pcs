package com.yunforge.cms.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-09T13:36:06.146+0800")
@StaticMetamodel(Info.class)
public class Info_ {
	public static volatile SingularAttribute<Info, String> id;
	public static volatile SingularAttribute<Info, String> title;
	public static volatile SingularAttribute<Info, String> authorId;
	public static volatile SingularAttribute<Info, Date> pubDate;
	public static volatile SingularAttribute<Info, Date> expiredDate;
	public static volatile SingularAttribute<Info, String> approverId;
	public static volatile SingularAttribute<Info, String> author;
	public static volatile SingularAttribute<Info, String> origin;
	public static volatile SingularAttribute<Info, InfoType> infoType;
	public static volatile SingularAttribute<Info, Integer> status;
	public static volatile SingularAttribute<Info, String> ip;
	public static volatile SingularAttribute<Info, Integer> favorite;
	public static volatile SingularAttribute<Info, Integer> counter;
	public static volatile SingularAttribute<Info, Integer> commentCount;
	public static volatile SingularAttribute<Info, BigDecimal> score;
	public static volatile SingularAttribute<Info, Boolean> notifyPub;
	public static volatile SingularAttribute<Info, String> body;
	public static volatile SingularAttribute<Info, String> summary;
	public static volatile SingularAttribute<Info, String> url;
	public static volatile SingularAttribute<Info, InfoCat> infoCat;
	public static volatile SetAttribute<Info, InfoFile> infoFiles;
	public static volatile SetAttribute<Info, Rating> ratings;
	public static volatile SetAttribute<Info, Comment> comments;
	public static volatile SingularAttribute<Info, Date> createDate;
	public static volatile SingularAttribute<Info, Boolean> deleted;
	public static volatile SingularAttribute<Info, Integer> weight;
}
