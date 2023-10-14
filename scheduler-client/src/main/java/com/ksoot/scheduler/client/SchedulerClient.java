package com.ksoot.scheduler.client;

import com.ksoot.scheduler.client.helper.JobTypeHelper;
import com.ksoot.scheduler.client.helper.TriggerHelper;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

public class SchedulerClient {

  public TriggerHelper newJob(final JobDetail jobDetail) {

    return null;
  }

  public JobTypeHelper newJob() {

    return null;
  }

  public void jobOf(final JobKey jobKey) {}

  public void trigger(final Trigger trigger) {}

  public static class Helper {}
}
