package com.yunforge.base.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-11-18T15:10:56.045+0800")
@StaticMetamodel(NoticeDivision.class)
public class NoticeDivision_ {
	public static volatile SingularAttribute<NoticeDivision, String> id;
	public static volatile SingularAttribute<NoticeDivision, Notice> notice;
	public static volatile SingularAttribute<NoticeDivision, Division> division;
	public static volatile SingularAttribute<NoticeDivision, Integer> readFlag;
	public static volatile SingularAttribute<NoticeDivision, Date> readDate;
}
