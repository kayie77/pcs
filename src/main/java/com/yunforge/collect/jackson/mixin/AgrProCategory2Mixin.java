package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yunforge.collect.model.AgrProCategory2;

public class AgrProCategory2Mixin {
	@JsonIgnoreProperties({"parent","children","taskDetails"})
	public static class BasicInfo {
	}
	
	public static class treeInfo extends AgrProCategory2{
		@JsonProperty("pid")
		public String getPid(){
			return getParent().getId();
		}
	}
	
}
