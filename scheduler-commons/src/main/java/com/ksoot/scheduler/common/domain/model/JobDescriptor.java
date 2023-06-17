package com.ksoot.scheduler.common.domain.model;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class JobDescriptor {

	private Identity identity;
	
	private String description;
	
	private JobType jobType;
	
	private JobDataMap map;
	
	public static JobDescriptor of(final JobDetail jobDetail) {
		return null;
	}
}
