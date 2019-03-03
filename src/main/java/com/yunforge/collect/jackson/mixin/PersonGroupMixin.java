package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PersonGroupMixin {
	@JsonIgnoreProperties({"gropInfo","dataCollectPerson"})
	public static class BasicInfo {
	}
	
}
