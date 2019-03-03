package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class DataReoprtedDetailMixin {
	@JsonIgnoreProperties({"dataReoprtedMain","taskDetail"})
	public static class BasicInfo {
	}
}
