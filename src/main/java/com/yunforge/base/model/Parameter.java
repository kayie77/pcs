package com.yunforge.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_PARA")
public class Parameter implements IEntity<String> {
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_HEAD_PATH = "TABLE_HEAD_PATH";

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "PARA_CODE", length = 20)
	private String paraCode;

	@Column(name = "PARA_NAME", length = 50)
	private String paraName;

	@Column(name = "PARA_VAL", length = 100)
	private String paraVal;

	@Column(name = "PARA_DESC")
	private String paraDesc;

	public Parameter() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraigName) {
		this.paraName = paraigName;
	}

	public String getParaVal() {
		return paraVal;
	}

	public void setParaVal(String paraVal) {
		this.paraVal = paraVal;
	}

	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

}