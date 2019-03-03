package com.yunforge.cms.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-06T13:48:54.421+0800")
@StaticMetamodel(InfoCat.class)
public class InfoCat_ {
	public static volatile SingularAttribute<InfoCat, String> id;
	public static volatile SingularAttribute<InfoCat, String> catName;
	public static volatile SingularAttribute<InfoCat, Integer> weight;
	public static volatile SingularAttribute<InfoCat, String> catDesc;
	public static volatile SingularAttribute<InfoCat, InfoCat> parent;
	public static volatile SetAttribute<InfoCat, InfoCat> children;
	public static volatile SetAttribute<InfoCat, Info> infos;
}
