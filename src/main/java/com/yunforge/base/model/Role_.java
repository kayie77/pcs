package com.yunforge.base.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-09T22:27:44.029+0800")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, String> id;
	public static volatile SingularAttribute<Role, String> roleName;
	public static volatile SingularAttribute<Role, String> roleNameCN;
	public static volatile SingularAttribute<Role, String> roleDesc;
	public static volatile SingularAttribute<Role, Boolean> enabled;
	public static volatile ListAttribute<Role, Org> orgs;
	public static volatile ListAttribute<Role, Group> groups;
	public static volatile ListAttribute<Role, User> users;
	public static volatile ListAttribute<Role, Resource> resources;
}
