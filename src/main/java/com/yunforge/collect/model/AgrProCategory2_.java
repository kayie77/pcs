package com.yunforge.collect.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-31T11:28:13.352+0800")
@StaticMetamodel(AgrProCategory2.class)
public class AgrProCategory2_ {
	public static volatile SingularAttribute<AgrProCategory2, String> id;
	public static volatile SingularAttribute<AgrProCategory2, String> code;
	public static volatile SingularAttribute<AgrProCategory2, String> name;
	public static volatile SingularAttribute<AgrProCategory2, String> explain;
	public static volatile SingularAttribute<AgrProCategory2, Integer> sort;
	public static volatile SingularAttribute<AgrProCategory2, Integer> state;
	public static volatile SingularAttribute<AgrProCategory2, AgrProCategory2> parent;
	public static volatile ListAttribute<AgrProCategory2, AgrProCategory2> children;
	public static volatile ListAttribute<AgrProCategory2, TaskDetail> taskDetails;
}
