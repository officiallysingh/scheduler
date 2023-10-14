package com.ksoot.scheduler.client.helper;

import com.ksoot.scheduler.common.domain.model.JobType;

public interface JobTypeHelper {

  public JobDataHelper ofType(final JobType type);

  public ServiceCallDetailsHelper serviceCallType();

  public ServiceCallDetailsHelper serviceCallType(final String serviceName);

  public JobDataHelper eventFireType();
}
