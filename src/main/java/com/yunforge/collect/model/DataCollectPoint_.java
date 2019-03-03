package com.yunforge.collect.model;

import com.yunforge.base.model.Division;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-08T09:15:02.800+0800")
@StaticMetamodel(DataCollectPoint.class)
public class DataCollectPoint_ {
	public static volatile SingularAttribute<DataCollectPoint, String> id;
	public static volatile SingularAttribute<DataCollectPoint, String> code;
	public static volatile SingularAttribute<DataCollectPoint, String> name;
	public static volatile SingularAttribute<DataCollectPoint, String> shortname;
	public static volatile SingularAttribute<DataCollectPoint, String> region;
	public static volatile SingularAttribute<DataCollectPoint, String> adress;
	public static volatile SingularAttribute<DataCollectPoint, String> linkman;
	public static volatile SingularAttribute<DataCollectPoint, String> telephone;
	public static volatile SingularAttribute<DataCollectPoint, Double> longitude;
	public static volatile SingularAttribute<DataCollectPoint, Double> latitude;
	public static volatile SingularAttribute<DataCollectPoint, Integer> state;
	public static volatile SingularAttribute<DataCollectPoint, String> remark;
	public static volatile SingularAttribute<DataCollectPoint, DataCollectCategory> dataCollectCategory;
	public static volatile SingularAttribute<DataCollectPoint, Division> division;
	public static volatile ListAttribute<DataCollectPoint, DataCollectPerson> dataCollectPersons;
	public static volatile ListAttribute<DataCollectPoint, Reoprted> reoprteds;
}
