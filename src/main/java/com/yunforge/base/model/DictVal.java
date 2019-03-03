package com.yunforge.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_DICT_VAL")
public class DictVal implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "VAL_CODE", length = 100)
	private String valCode;

	@Column(name = "VAL_NAME", length = 100)
	private String valName;

	@Column(name = "LEVEL_NUM")
	private Integer levelNum;

	@Column(name = "VAL_DESC", length = 255)
	private String valDesc;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "DICT_ID")
	private Dict dict;

	@Column(name = "VER_NUM")
	private Integer verNum;

	@Column(name = "WEIGHT")
	private Integer weight;

	public DictVal() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getValCode() {
		return valCode;
	}

	public void setValCode(String valCode) {
		this.valCode = valCode;
	}

	public String getValName() {
		return valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public Integer getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}

	public String getValDesc() {
		return valDesc;
	}

	public void setValDesc(String valDesc) {
		this.valDesc = valDesc;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public Integer getVerNum() {
		return verNum;
	}

	public void setVerNum(Integer verNum) {
		this.verNum = verNum;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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
		DictVal other = (DictVal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

}