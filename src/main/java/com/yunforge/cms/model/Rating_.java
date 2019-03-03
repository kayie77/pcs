package com.yunforge.cms.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-05T21:59:37.866+0800")
@StaticMetamodel(Rating.class)
public class Rating_ {
	public static volatile SingularAttribute<Rating, String> id;
	public static volatile SingularAttribute<Rating, Info> article;
	public static volatile SingularAttribute<Rating, String> ip;
	public static volatile SingularAttribute<Rating, Integer> rating;
	public static volatile SingularAttribute<Rating, Date> ratingTime;
	public static volatile SingularAttribute<Rating, String> uid;
}
