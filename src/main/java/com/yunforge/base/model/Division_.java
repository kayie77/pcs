package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-16T20:49:13.663+0800")
@StaticMetamodel(Division.class)
public class Division_ {
	public static volatile SingularAttribute<Division, String> id;
	public static volatile SingularAttribute<Division, String> divName;
	public static volatile SingularAttribute<Division, String> divCode;
	public static volatile SingularAttribute<Division, String> mnemonicCode;
	public static volatile SingularAttribute<Division, Division> parent;
	public static volatile ListAttribute<Division, Division> children;
	public static volatile SingularAttribute<Division, String> divDesc;
	public static volatile SingularAttribute<Division, Integer> verNum;
	public static volatile SingularAttribute<Division, String> wordNum;
	public static volatile SingularAttribute<Division, Integer> weight;
	public static volatile SingularAttribute<Division, Integer> newOrder;
}
