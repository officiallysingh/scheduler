package com.ksoot.scheduler.common.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ScheduleJob {

	@NotNull
	private JobDetail jobDetail;
	
	@NotEmpty
	private Trigger[] triggers;
	
	@JsonCreator
    public static ScheduleJob of(@JsonProperty("group") JobDetail jobDetail,
    		@JsonProperty("name") Trigger[] triggers) {
    	return new ScheduleJob(jobDetail, triggers);
    }
	
}