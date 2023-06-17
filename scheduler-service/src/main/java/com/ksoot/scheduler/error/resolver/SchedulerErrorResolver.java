//package com.ksoot.scheduler.error.resolver;
//
//import org.quartz.SchedulerException;
//import org.zalando.problem.Status;
//import org.zalando.problem.StatusType;
//
//import com.ksoot.common.error.ApplicationProblem;
//import com.ksoot.common.error.resolver.ErrorResolver;
//import com.ksoot.common.error.resolver.GeneralTitleMessageResolver;
//import com.ksoot.common.message.MessageProvider;
//import com.ksoot.common.message.MessageResolver;
//import com.ksoot.scheduler.common.SchedulerConstant;
//
//public enum SchedulerErrorResolver implements ErrorResolver {
//
//	// @formatter:off
//	SCHEDULER_EXCEPTION(SchedulerErrorMessageResolver.SCHEDULER_EXCEPTION,
//			SchedulerConstant.TYPE_SCHEDULER_ERROR,
//			SchedulerTitleResolver.SCHEDULER_EXCEPTION,
//			Status.INTERNAL_SERVER_ERROR);
//	// @formatter:on
//
//	private final MessageResolver messageResolver;
//
//	private final String typeCode;
//
//	private MessageResolver titleResolver;
//
//	private StatusType status;
//
//	private Object[] params;
//
//	private SchedulerErrorResolver(final MessageResolver messageResolver, final String typeCode,
//			final MessageResolver titleResolver, final StatusType status) {
//		this.messageResolver = messageResolver;
//		this.typeCode = typeCode;
//		this.titleResolver = titleResolver;
//		this.status = status;
//	}
//
//	@Override
//	public String message() {
//		return MessageProvider.getMessage(null, this.messageResolver.defaultMessage(), this.params);
//	}
//
//	@Override
//	public String localizedMessage() {
//		return MessageProvider.getMessage(this.messageResolver, this.params);
//	}
//
//	@Override
//	public ErrorResolver parameters(final Object... params) {
//		this.params = params;
//		return this;
//	}
//
//	@Override
//	public String typeCode() {
//		return this.typeCode;
//	}
//
//	@Override
//	public StatusType status() {
//		return this.status != null ? this.status : Status.INTERNAL_SERVER_ERROR;
//	}
//
//	@Override
//	public String title() {
//		return this.titleResolver != null ? MessageProvider.getMessage(this.titleResolver)
//				: MessageProvider.getMessage(GeneralTitleMessageResolver.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public SchedulerErrorResolver status(StatusType status) {
//		this.status = status;
//		return this;
//	}
//
//	@Override
//	public ErrorResolver titleResolver(MessageResolver titleResolver) {
//		this.titleResolver = titleResolver;
//		return this;
//	}
//
//	// ------------ Problem factory methods -------------------
//
//	public static ApplicationProblem schedulerProblem(final SchedulerException e) throws ApplicationProblem {
//		return new ApplicationProblem(SchedulerErrorResolver.SCHEDULER_EXCEPTION, e);
//	}
//
//}
