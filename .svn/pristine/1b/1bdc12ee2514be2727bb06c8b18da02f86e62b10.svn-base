/**   
 * @Title: InfoType.java 
 * @Package com.yunforge.cms.model 
 * @Description: TODO 
 * @author Oliver Wen  
 * @date 2015年10月8日 上午2:50:29 
 * @version V1.0   
 */
package com.yunforge.cms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @ClassName: InfoType
 * @Description: TODO
 * @author Oliver Wen
 * @date 2015年10月8日 上午2:50:29
 * 
 */
@Embeddable 
public class InfoType implements Serializable {
	private static final long serialVersionUID = 7026837578078454628L;

	@Column(name = "TYPE")
	private Integer type;

	@Column(name = "OBJ_ID")
	private String objId;

	public InfoType() {

	}

	public InfoType(Integer type, String objId) {

		this.type = type;

		this.objId = objId;

	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	@Override
	public String toString() {
		return "InfoType [type=" + type + "]";
	}

}
