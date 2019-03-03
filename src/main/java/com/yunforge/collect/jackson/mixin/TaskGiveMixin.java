package com.yunforge.collect.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TaskGiveMixin {
	@JsonIgnoreProperties({"taskMain","dataCollectPerson"})
	public static class BasicInfo {
	}
}
