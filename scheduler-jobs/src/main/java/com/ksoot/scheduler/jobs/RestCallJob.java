package com.ksoot.scheduler.jobs;

import com.ksoot.common.util.DateTimeUtils;
import java.time.ZonedDateTime;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// @Component
// @ExecuteInJTATransaction
public class RestCallJob extends QuartzJobBean {

  private String targetServiceUrl;

  //	private HttpMethod httpMethod;

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
