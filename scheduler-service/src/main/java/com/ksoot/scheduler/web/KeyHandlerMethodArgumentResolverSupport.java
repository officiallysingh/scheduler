package com.ksoot.scheduler.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public abstract class KeyHandlerMethodArgumentResolverSupport {

	private static final String GROUP_PARAMETER = "group";
	
	private static final String NAME_PARAMETER = "name";
	
	protected String getGroup(final Map<String, String[]> queryParams) {
		Optional<String> group = Arrays.stream(queryParams.get(GROUP_PARAMETER)).findFirst();
		if(group.isEmpty()) {
			throw new IllegalArgumentException("Request parameter '" + GROUP_PARAMETER + "' is required");
		} else {
			return group.get();
		}
	}

	protected String getName(final Map<String, String[]> queryParams) {
		Optional<String> name = Arrays.stream(queryParams.get(NAME_PARAMETER)).findFirst();
		if(name.isEmpty()) {
			throw new IllegalArgumentException("Request parameter '" + NAME_PARAMETER + "' is required");
		} else {
			return name.get();
		}
	}
}
