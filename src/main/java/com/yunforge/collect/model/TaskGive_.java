package com.yunforge.collect.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-05T09:44:32.009+0800")
@StaticMetamodel(TaskGive.class)
public class TaskGive_ {
	public static volatile SingularAttribute<TaskGive, String> id;
	public static volatile SingularAttribute<TaskGive, TaskMain> taskMain;
	public static volatile SingularAttribute<TaskGive, DataCollectPerson> dataCollectPerson;
}
