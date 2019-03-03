package com.yunforge.collect.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-16T10:08:23.812+0800")
@StaticMetamodel(DataReoprtedDetail.class)
public class DataReoprtedDetail_ {
	public static volatile SingularAttribute<DataReoprtedDetail, String> id;
	public static volatile SingularAttribute<DataReoprtedDetail, String> data;
	public static volatile SingularAttribute<DataReoprtedDetail, DataReoprtedMain> dataReoprtedMain;
	public static volatile SingularAttribute<DataReoprtedDetail, TaskDetail> taskDetail;
	public static volatile SingularAttribute<DataReoprtedDetail, Integer> editable;
}
