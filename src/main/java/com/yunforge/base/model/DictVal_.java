package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-07T01:51:40.539+0800")
@StaticMetamodel(DictVal.class)
public class DictVal_ {
	public static volatile SingularAttribute<DictVal, String> id;
	public static volatile SingularAttribute<DictVal, String> valCode;
	public static volatile SingularAttribute<DictVal, String> valName;
	public static volatile SingularAttribute<DictVal, Integer> levelNum;
	public static volatile SingularAttribute<DictVal, String> valDesc;
	public static volatile SingularAttribute<DictVal, Dict> dict;
	public static volatile SingularAttribute<DictVal, Integer> verNum;
	public static volatile SingularAttribute<DictVal, Integer> weight;
}
