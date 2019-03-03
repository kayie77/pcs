package com.yunforge.collect.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.collect.jackson.serializer.CustomJsonDateSerializer;
import com.yunforge.collect.jackson.serializer.DataIDSerializer;
import com.yunforge.collect.jackson.serializer.TaskStatusSerializer;
import com.yunforge.collect.jackson.serializer.NullableSerializer;

@Entity
@Table(name = "Oper_TaskDetail")
public class TaskDetail {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name="ID", nullable = false, updatable = false, length = 100)
	private String id;
	
	@JsonSerialize(using = DataIDSerializer.class)
	@Column(name="DATAID")
	private String dataID;//采集指标
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BEGINDATE", nullable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date beginDate;//开始日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENDDATE", nullable = true)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private Date endDate;//结束日期
	
	@Column(name="CRON", length = 200)
	private String cron;//cron表达式
		
	@Column(name="TIMEFRAME", length = 200)
	private String timeframe;//时间段
	
	@JsonSerialize(using = NullableSerializer.class)
	@Column(name="NULLABLE")
	private Integer nullable;// 是否必填，1-必填，2选填
	
	@Column(name="CHECKEXP", length = 200)
	private String checkExp;//数据检验表达式
	
	@Transient
	private String dataName;
	
	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	@Column(name="REMARK", length = 500)
	private String remark;//备份
	
/*	//AgrProID
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "AGRPROREPORT")
	private AgrProReport agrProReport;//农产品外键(废弃)
*/	
	//AGRPROCATEGORY
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "AGRPROCATEGORY2")
	private AgrProCategory2 agrProCategory2;//农产品外键
	
	//TaskID
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "TASKMAIN")
	private TaskMain taskMain;//所属任务
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="taskDetail", fetch=FetchType.LAZY)  
    private List<DataReoprtedDetail> dataReoprtedDetails;//填报主表
	
	public static final class DATAID{
		public static final int YIELD  = 11;
		public static final int SALECOUNT = 21;
		public static final int WHOLESALEPRICE  = 31;
		public static final int MARKETVALUE  = 41;
		public static final int HIGHESTPRICE  = 42;
		public static final int LOWESTPRICE  = 43;
		public static final int AVGPRICE  = 44;
		public static final int STAPLEPRICE  = 45;
	}
	
	public static final class NULLABLE{
		public static final int MUST  = 1;
		public static final int SELECT = 2;
	}
	
	
	@JsonProperty("agrProCategory2Name")
	public String getAgrProCategory2Name(){
		return getAgrProCategory2().getName();
	}
	
	@JsonProperty("taskMainId")
	public String getTaskMainId(){
		return getTaskMain().getId();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TaskMain getTaskMain() {
		return taskMain;
	}

	public void setTaskMain(TaskMain taskMain) {
		this.taskMain = taskMain;
	}
	
	public List<DataReoprtedDetail> getDataReoprtedDetails() {
		return dataReoprtedDetails;
	}

	public void setDataReoprtedDetails(List<DataReoprtedDetail> dataReoprtedDetails) {
		this.dataReoprtedDetails = dataReoprtedDetails;
	}

	public AgrProCategory2 getAgrProCategory2() {
		return agrProCategory2;
	}

	public void setAgrProCategory2(AgrProCategory2 agrProCategory2) {
		this.agrProCategory2 = agrProCategory2;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Integer getNullable() {
		return nullable;
	}

	public void setNullable(Integer nullable) {
		this.nullable = nullable;
	}

	public String getCheckExp() {
		return checkExp;
	}

	public void setCheckExp(String checkExp) {
		this.checkExp = checkExp;
	}
	
	
	
	

}
