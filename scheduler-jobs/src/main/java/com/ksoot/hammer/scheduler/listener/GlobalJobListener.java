package com.ksoot.hammer.scheduler.listener;

import org.quartz.listeners.JobListenerSupport;

public class GlobalJobListener extends JobListenerSupport {

  private static final String GLOBAL_JOB_LISTENER_NAME = "GLOBAL_JOB_LISTENER";

  @Override
  public String getName() {
    return GLOBAL_JOB_LISTENER_NAME;
  }
}
