package com.ksoot.hammer.scheduler.listener;

import org.quartz.listeners.TriggerListenerSupport;

public class GlobalTriggerListener extends TriggerListenerSupport {

  private static final String GLOBAL_TRIGGER_LISTENER_NAME = "GLOBAL_TRIGGER_LISTENER";

  @Override
  public String getName() {
    return GLOBAL_TRIGGER_LISTENER_NAME;
  }
}
