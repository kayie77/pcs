package com.yunforge.base.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-09-20T03:49:43.755+0800")
@StaticMetamodel(Event.class)
public class Event_ {
	public static volatile SingularAttribute<Event, String> id;
	public static volatile SingularAttribute<Event, Boolean> alldayEvent;
	public static volatile SingularAttribute<Event, String> description;
	public static volatile SingularAttribute<Event, Date> endDate;
	public static volatile SingularAttribute<Event, Integer> eventType;
	public static volatile SingularAttribute<Event, Boolean> open;
	public static volatile SingularAttribute<Event, String> place;
	public static volatile SingularAttribute<Event, Integer> repeatType;
	public static volatile SingularAttribute<Event, Date> saveTime;
	public static volatile SingularAttribute<Event, Date> startDate;
	public static volatile SingularAttribute<Event, String> title;
	public static volatile SingularAttribute<Event, String> username;
}
