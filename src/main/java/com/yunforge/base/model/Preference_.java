package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-08T13:50:44.937+0800")
@StaticMetamodel(Preference.class)
public class Preference_ {
	public static volatile SingularAttribute<Preference, String> id;
	public static volatile SingularAttribute<Preference, String> prefName;
	public static volatile SingularAttribute<Preference, String> prefVal;
	public static volatile SingularAttribute<Preference, String> prefDesc;
	public static volatile SingularAttribute<Preference, User> user;
}
