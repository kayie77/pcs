package com.yunforge.collect.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-16T10:08:23.820+0800")
@StaticMetamodel(GropInfo.class)
public class GropInfo_ {
	public static volatile SingularAttribute<GropInfo, String> id;
	public static volatile SingularAttribute<GropInfo, String> code;
	public static volatile SingularAttribute<GropInfo, String> name;
	public static volatile SingularAttribute<GropInfo, String> explain;
	public static volatile SingularAttribute<GropInfo, Integer> state;
	public static volatile ListAttribute<GropInfo, PersonGroup> personGroups;
}
