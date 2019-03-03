package com.yunforge.cms.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-06T13:43:19.043+0800")
@StaticMetamodel(InfoFile.class)
public class InfoFile_ {
	public static volatile SingularAttribute<InfoFile, String> id;
	public static volatile SingularAttribute<InfoFile, String> fileName;
	public static volatile SingularAttribute<InfoFile, String> contentType;
	public static volatile SingularAttribute<InfoFile, Integer> depositType;
	public static volatile SingularAttribute<InfoFile, byte[]> content;
	public static volatile SingularAttribute<InfoFile, String> filePath;
	public static volatile SingularAttribute<InfoFile, String> fileDesc;
	public static volatile SingularAttribute<InfoFile, Date> created;
	public static volatile SingularAttribute<InfoFile, Boolean> deleted;
	public static volatile SingularAttribute<InfoFile, Info> info;
}
