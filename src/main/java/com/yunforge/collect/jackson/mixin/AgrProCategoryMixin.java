package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AgrProCategoryMixin {
	@JsonIgnoreProperties({"personGroups"})
	public static class BasicInfo {
	}
	
}
