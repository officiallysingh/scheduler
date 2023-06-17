package com.ksoot.scheduler;

import static com.ksoot.scheduler.common.CommonConstant.API;
import static com.ksoot.scheduler.common.CommonConstant.VERSION_v1;

public interface SchedulerConstant {

	String TYPE_SCHEDULER_ERROR = "scheduler-error";

	String CURRENT_VERSION = VERSION_v1;

	String SCHEDULER_API = API + CURRENT_VERSION;

}
