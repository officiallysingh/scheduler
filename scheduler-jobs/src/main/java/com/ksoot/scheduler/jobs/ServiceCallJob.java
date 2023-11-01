package com.ksoot.scheduler.jobs;

import com.ksoot.common.util.DateTimeUtils;
import java.time.ZonedDateTime;
import lombok.NoArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// @Component
// @ExecuteInJTATransaction
@NoArgsConstructor
public class ServiceCallJob extends QuartzJobBean {

  // Don't add any instance variable it does not make sense to have state data-fields defined on the
  // job class -
  // as their values would not be preserved between job executions.
  // You may now be wanting to ask “how can I provide properties/configuration for a Job instance?”
  // and “how can I keep track of a job’s state between executions?”
  // The answer to these questions are the same: the key is the JobDataMap, which is part of the
  // JobDetail object.

  @Override
  protected void executeInternal(final JobExecutionContext jobExecutionContext)
      throws JobExecutionException {
    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
    String rateId = jobExecutionContext.getTrigger().getKey().getName();
    //        String ricId = jobDataMap.getString(QUARTZ_JOB_DETAILS_RIC_ID_KEY);
    // long frequency = jobDataMap.getLong(QUARTZ_JOB_DETAILS_FREQUENCY_KEY);
    String fireInstanceId = jobExecutionContext.getFireInstanceId();
    //        jobExecutionContext.getTrigger().
    ZonedDateTime fireTime =
        jobExecutionContext.getFireTime().toInstant().atZone(DateTimeUtils.ZONE_ID_UTC);
    ZonedDateTime nextFireTime =
        jobExecutionContext.getNextFireTime().toInstant().atZone(DateTimeUtils.ZONE_ID_UTC);
  }
}
