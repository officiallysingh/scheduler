//package com.ksoot.scheduler.message.resolver;
//
//import com.ksoot.common.message.MessageResolver;
//
//public enum SchedulerMessageResolver implements MessageResolver {
//
//	// @formatter:off
//		JOB_ADDED("job.created.successfully",
//				"Job created successfully"),
//		JOB_UPDATED("job.updated.successfully",
//				"Job updated successfully"),
//		JOB_DELETED("Job.deleted.successfully",
//				"Job deleted successfully"),
//		JOB_SCHEDULED("job.scheduled.successfully",
//				"Job scheduled successfully"),
//		JOB_UN_SCHEDULED("job.unscheduled.successfully",
//				"Job unscheduled successfully"),
//		JOB_RE_SCHEDULED("job.rescheduled.successfully",
//				"Job rescheduled successfully"),
//		TRIGGER_UPDATED("trigger.updated.successfully",
//				"Trigger updated successfully"),
//		TRIGGER_DELETED("trigger.deleted.successfully",
//				"Trigger deleted successfully");
//		// @formatter:on
//
//	private String messageCode;
//
//	private String defaultMessage;
//
//	private SchedulerMessageResolver(final String messageCode, final String defaultMessage) {
//		this.messageCode = messageCode;
//		this.defaultMessage = defaultMessage;
//	}
//
//	@Override
//	public String messageCode() {
//		return this.messageCode;
//	}
//
//	@Override
//	public String defaultMessage() {
//		return this.defaultMessage;
//	}
//
//}
