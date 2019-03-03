package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-01T01:47:56.547+0800")
@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, String> id;
	public static volatile SingularAttribute<Group, String> groupName;
	public static volatile SingularAttribute<Group, String> groupDesc;
	public static volatile SingularAttribute<Group, Boolean> enabled;
	public static volatile ListAttribute<Group, Role> roles;
	public static volatile ListAttribute<Group, User> users;
}
