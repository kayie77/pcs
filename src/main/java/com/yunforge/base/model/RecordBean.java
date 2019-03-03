package com.yunforge.base.model;

public class RecordBean {

	//cropcode divcode indexcode reportcode reportnum
	private String cropCode;
	private String indexCode;
	private String reportCode;
	private String reportNum;
	
	public String getCropCode() {
		return cropCode;
	}
	public void setCropCode(String cropCode) {
		this.cropCode = cropCode;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	
	@Override
	public int hashCode() {
		return cropCode.hashCode() + indexCode.hashCode() + reportCode.hashCode() + reportNum.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		RecordBean reordBean = (RecordBean)obj;
		if(cropCode.equals(reordBean.getCropCode())
				&& indexCode.equals(reordBean.getIndexCode())
				&& reportCode.equals(reordBean.getReportCode())
				&& reportNum.equals(reordBean.getReportNum())) {
			return true;
		}
		return false;
	}
	
	
}
