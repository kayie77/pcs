package com.yunforge.base.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-09-20T03:49:44.327+0800")
@StaticMetamodel(Year.class)
public class Year_ {
	public static volatile SingularAttribute<Year, String> id;
	public static volatile SingularAttribute<Year, Integer> yearNum;
	public static volatile SingularAttribute<Year, String> yearName;
	public static volatile SingularAttribute<Year, Boolean> enabled;
	public static volatile SingularAttribute<Year, Date> startDate;
	public static volatile SingularAttribute<Year, Date> endDate;
	public static volatile SingularAttribute<Year, Boolean> initialized;
}
