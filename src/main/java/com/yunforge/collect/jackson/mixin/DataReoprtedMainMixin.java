package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class DataReoprtedMainMixin {
	@JsonIgnoreProperties({"taskMain","person","dataReoprtedDetails"})
	public static class BasicInfo {
	}
}
