package com.ksoot.scheduler.client.helper;

import org.quartz.Trigger;

public interface TriggerHelper {
	
	ScheduleHelper trigger(final Trigger trigger);

	ScheduleHelper triggers(final Trigger... triggers);
}
