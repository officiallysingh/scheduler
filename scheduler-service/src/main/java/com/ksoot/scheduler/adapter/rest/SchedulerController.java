/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ksoot.scheduler.adapter.rest;

import com.ksoot.scheduler.common.domain.model.Identity;
import com.ksoot.scheduler.common.domain.model.ScheduleJobRQ;
import com.ksoot.scheduler.domain.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Rajveer Singh
 */
@RestController("/v1")
@RequiredArgsConstructor
public class SchedulerController implements SchedulerApi {

  private final SchedulerService schedulerService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.initDirectFieldAccess();
  }

//  @GetMapping("/jobs")
//  public ResponseEntity<JobDescriptor> getJob(final JobKey jobKey) {
//    this.schedulerService.getJobDetail(jobKey);
//    return null;
//  }

  @DeleteMapping("/jobs")
  public ResponseEntity<Void> deleteJob(final Identity identity) {
    this.schedulerService.getJobDetail(identity.jobKey());
    return null;
  }

//  @GetMapping("/triggers")
//  public ResponseEntity<TriggerDescriptor> getTrigger(final Identity identity) {
//    this.schedulerService.getTrigger(identity.triggerKey());
//    return null;
//  }

  @DeleteMapping("/triggers")
  public ResponseEntity<Void> deleteTrigger(final Identity identity) {
    this.schedulerService.getTrigger(identity.triggerKey());
    return null;
  }

  public void createSchedule() {}

  public void createTrigger() {}

  public void deleteTrigger() {}

  public void createJob() {}

  public void scheduleJob(final ScheduleJobRQ scheduleJobRQ) {
    if(ArrayUtils.isNotEmpty(scheduleJobRQ.triggers())) {
      this.schedulerService.scheduleJob(scheduleJobRQ.jobDetail(), scheduleJobRQ.triggers()[0]);
    } else {
      this.schedulerService.scheduleJob(scheduleJobRQ.jobDetail(), true, scheduleJobRQ.triggers());
    }
  }

  public void reScheduleJob(final Identity oldTriggerKey, final Trigger newTrigger) {
    if(Objects.nonNull(oldTriggerKey)) {
      this.schedulerService.reScheduleJob(oldTriggerKey.triggerKey(), newTrigger);
    } else {
      this.schedulerService.reScheduleJob(newTrigger);
    }
  }

  public void deleteJob() {}
}
