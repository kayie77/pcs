package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-07T01:51:40.378+0800")
@StaticMetamodel(Dict.class)
public class Dict_ {
	public static volatile SingularAttribute<Dict, String> id;
	public static volatile SingularAttribute<Dict, String> dictCode;
	public static volatile SingularAttribute<Dict, String> dictName;
	public static volatile SingularAttribute<Dict, DictType> dictType;
	public static volatile ListAttribute<Dict, DictVal> dictVals;
	public static volatile SingularAttribute<Dict, String> dictDesc;
	public static volatile SingularAttribute<Dict, Integer> weight;
}
