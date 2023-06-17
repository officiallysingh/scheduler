package com.ksoot.scheduler.client.helper;

import java.util.Map;

import org.quartz.JobDataMap;

public interface JobDataHelper {

	public void jobData(final JobDataMap parameters);
	
	public void jobData(final Map<String, Object> parameters);
}
