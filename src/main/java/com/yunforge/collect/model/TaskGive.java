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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.collect.jackson.serializer.CustomJsonDateSerializer;
import com.yunforge.collect.jackson.serializer.CustomJsonDateTimeSerializer;
import com.yunforge.collect.jackson.serializer.DownStatusSerializer;
import com.yunforge.collect.jackson.serializer.ReceiveStatusSerializer;
import com.yunforge.collect.jackson.serializer.ReportedStatusSerializer;

@Entity
@Table(name = "Oper_TaskGive")
public class TaskGive {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name="ID", nullable = false, updatable = false, length = 100)
	private String id;
	
	//TaskID
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "TASKMAIN")
	private TaskMain taskMain;//所属任务
	
	//PersonID	
	@ManyToOne(fetch=FetchType.LAZY, optional = true)
	@JoinColumn(name = "DATACOLLECTPERSON")
	private DataCollectPerson dataCollectPerson;//任务对象(采集员)
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TaskMain getTaskMain() {
		return taskMain;
	}

	public void setTaskMain(TaskMain taskMain) {
		this.taskMain = taskMain;
	}
	
	public DataCollectPerson getDataCollectPerson() {
		return dataCollectPerson;
	}

	public void setDataCollectPerson(DataCollectPerson dataCollectPerson) {
		this.dataCollectPerson = dataCollectPerson;
	}
}
