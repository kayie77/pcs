package com.yunforge.collect.model;

import com.yunforge.base.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-13T11:36:51.747+0800")
@StaticMetamodel(TaskMain.class)
public class TaskMain_ {
	public static volatile SingularAttribute<TaskMain, String> id;
	public static volatile SingularAttribute<TaskMain, String> name;
	public static volatile SingularAttribute<TaskMain, String> code;
	public static volatile SingularAttribute<TaskMain, String> createdOn;
	public static volatile SingularAttribute<TaskMain, User> createdBy;
	public static volatile SingularAttribute<TaskMain, Date> beginDate;
	public static volatile SingularAttribute<TaskMain, Date> endDate;
	public static volatile SingularAttribute<TaskMain, String> cron;
	public static volatile SingularAttribute<TaskMain, Integer> executeType;
	public static volatile SingularAttribute<TaskMain, Integer> state;
	public static volatile SingularAttribute<TaskMain, String> description;
	public static volatile SingularAttribute<TaskMain, String> remark;
	public static volatile ListAttribute<TaskMain, TaskDetail> taskDetails;
	public static volatile ListAttribute<TaskMain, TaskGive> taskGives;
	public static volatile ListAttribute<TaskMain, DataReoprtedMain> dataReoprtedMains;
}
