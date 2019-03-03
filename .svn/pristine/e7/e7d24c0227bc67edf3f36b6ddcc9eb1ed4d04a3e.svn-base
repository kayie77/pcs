package com.yunforge.collect.dto;

import java.util.Date;

public class PeriodSet {

	private Integer  period;
	
	private Date beginDate;
	
	private Date endDate;

	
	
	public PeriodSet(Integer period, Date beginDate, Date endDate) {
		super();
		this.period = period;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
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
	
	 //注意这里重写了equals方法  
    @Override  
    public boolean equals(Object obj){  
        if(obj == null){  
            return false;  
        }else {           
                if(this.getClass() == obj.getClass()){  
                	PeriodSet u = (PeriodSet) obj;  
                    if(this.getPeriod().equals(u.getPeriod())){  
                        return true;  
                    }else{  
                        return false;  
                    }  
                  
            }else{  
                return false;  
            }  
        }             
    }  
	
    @Override
    public String toString() {
    	return getPeriod()+"";
//    	return "period:"+getPeriod()+",b:"+getBeginDate()+",e:"+getEndDate();
    }
	
}
