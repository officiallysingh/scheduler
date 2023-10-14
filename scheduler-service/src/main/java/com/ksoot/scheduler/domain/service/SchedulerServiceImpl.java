package com.ksoot.scheduler.domain.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ksoot.common.util.DateTimeUtils;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

  private Scheduler scheduler;

  public SchedulerServiceImpl(final Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#doesJobExist(org.quartz.
   * JobKey)
   */
  @Override
  public Boolean doesJobExist(final JobKey jobKey) {
    try {
      return this.scheduler.checkExists(jobKey);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#getAllJobs(org.quartz.impl.matchers.GroupMatcher)
   */
  @Override
  public List<JobDetail> getAllJobs(final GroupMatcher<JobKey> matcher) {
    try {
      return this.scheduler.getJobKeys(matcher).stream().map(this::getJobDetail).toList();
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ksoot.scheduler.domain.service.SchedulerService#addJob(org.quartz.
   * JobDetail)
   */
  @Override
  public Boolean addJob(final JobDetail jobDetail) {
    try {
      this.scheduler.addJob(jobDetail, false);
      log.info("Job with key: {} added successfully", jobDetail.getKey());
      return true;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#updateJob(org.quartz.
   * JobDetail)
   */
  @Override
  public Boolean updateJob(final JobDetail jobDetail) {
    try {
      this.scheduler.addJob(jobDetail, true);
      log.info("Job with key: {} updated successfully", jobDetail.getKey());
      return true;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#getJobDetail(org.quartz.JobKey)
   */
  @Override
  public JobDetail getJobDetail(final JobKey jobKey) {
    try {
      return this.scheduler.getJobDetail(jobKey);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#deleteJob(org.quartz.
   * JobKey)
   */
  @Override
  public Boolean deleteJob(final JobKey jobKey) {
    try {
      boolean isDeleted = this.scheduler.deleteJob(jobKey);
      log.info(
          "Job with key: {} {}",
          jobKey,
          isDeleted ? "deleted successfully" : "could not be deleted");
      return isDeleted;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#deleteJob(org.quartz.
   * JobKey)
   */
  @Override
  public Boolean deleteJobs(final JobKey... jobKeys) {
    try {
      List<JobKey> jobKeysList = Lists.newArrayList(jobKeys);
      boolean isDeleted = this.scheduler.deleteJobs(jobKeysList);
      log.info(
          "Jobs with keys: {} {}",
          jobKeysList,
          isDeleted ? "deleted successfully" : "could not be deleted");
      return isDeleted;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#scheduleJob(org.quartz.JobDetail, org.quartz.Trigger)
   */
  @Override
  public OffsetDateTime scheduleJob(final JobDetail jobDetail, final Trigger trigger) {
    try {
      Date nextFireTime = this.scheduler.scheduleJob(jobDetail, trigger);
      if (nextFireTime != null) {
        log.info(
            "Job with key: {} scheduled successfully with Trigger key: {}",
            jobDetail.getKey(),
            trigger.getKey());
      } else {
        log.info(
            "Job with key: {} could not be scheduled with Trigger key: {}",
            jobDetail.getKey(),
            trigger.getKey());
      }
      return nextFireTime == null ? null : DateTimeUtils.offsetDateTimeUTC(nextFireTime);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#scheduleJob(boolean, org.quartz.JobDetail, org.quartz.Trigger[])
   */
  @Override
  public Boolean scheduleJob(
      final JobDetail jobDetail, boolean replace, final Trigger... triggers) {
    try {
      this.scheduler.scheduleJob(jobDetail, Sets.newHashSet(triggers), replace);
      log.info("Job with key: {} scheduled successfully with given triggers", jobDetail.getKey());
      return true;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#reScheduleJob(org.quartz
   * .JobKey, org.quartz.Trigger)
   */
  @Override
  public OffsetDateTime reScheduleJob(final Trigger newTrigger) {
    try {
      Date nextFireTime = this.scheduler.rescheduleJob(newTrigger.getKey(), newTrigger);
      if (nextFireTime != null) {
        log.info(
            "Job with key: {} scheduled successfully with Trigger key: {}",
            newTrigger.getJobKey(),
            newTrigger.getKey());
      } else {
        log.info(
            "Job with key: {} could not be scheduled with Trigger key: {}",
            newTrigger.getJobKey(),
            newTrigger.getKey());
      }
      return nextFireTime == null ? null : DateTimeUtils.offsetDateTimeUTC(nextFireTime);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#reScheduleJob(org.quartz.TriggerKey, org.quartz.Trigger)
   */
  @Override
  public OffsetDateTime reScheduleJob(final TriggerKey oldTriggerKey, final Trigger newTrigger) {
    try {
      Date nextFireTime = this.scheduler.rescheduleJob(oldTriggerKey, newTrigger);
      if (nextFireTime != null) {
        log.info(
            "Job with key: {} rescheduled successfully with Trigger key: {}",
            oldTriggerKey,
            newTrigger.getKey());
      } else {
        log.info(
            "Job with key: {} could not be rescheduled with Trigger key: {}",
            oldTriggerKey,
            newTrigger.getKey());
      }
      return nextFireTime == null ? null : DateTimeUtils.offsetDateTimeUTC(nextFireTime);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#unScheduleJob(org.quartz
   * .TriggerKey)
   */
  @Override
  public Boolean unScheduleJob(final TriggerKey triggerKey) {
    try {
      boolean isUnScheduled = this.scheduler.unscheduleJob(triggerKey);
      log.info(
          "Job {} for trigger key: {}",
          isUnScheduled ? "unscheduled successfully" : "could not be unscheduled",
          triggerKey);
      return isUnScheduled;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#unScheduleJobs(org.quartz.TriggerKey[])
   */
  @Override
  public Boolean unScheduleJobs(final TriggerKey... triggerKeys) {
    List<TriggerKey> triggerKeysList = Lists.newArrayList(triggerKeys);
    try {
      boolean isUnScheduled = this.scheduler.unscheduleJobs(triggerKeysList);
      log.info(
          "Job {} for trigger keys: {}",
          isUnScheduled ? "unscheduled successfully" : "could not be unscheduled",
          triggerKeysList);
      return isUnScheduled;
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#doesTriggerExist(org.
   * quartz.TriggerKey)
   */
  @Override
  public Boolean doesTriggerExist(final TriggerKey triggerKey) {
    try {
      return this.scheduler.checkExists(triggerKey);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#updateTrigger(org.quartz
   * .Trigger)
   */
  @Override
  public OffsetDateTime updateTrigger(final Trigger newTrigger) {
    return this.reScheduleJob(newTrigger);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#deleteTrigger(org.quartz
   * .TriggerKey)
   */
  @Override
  public Boolean deleteTrigger(final TriggerKey triggerKey) {
    return this.unScheduleJob(triggerKey);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#getTrigger(org.quartz.
   * TriggerKey)
   */
  @Override
  public Trigger getTrigger(final TriggerKey triggerKey) {
    try {
      return this.scheduler.getTrigger(triggerKey);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.ksoot.scheduler.domain.service.SchedulerService#getTriggerState(org.
   * quartz.TriggerKey)
   */
  @Override
  public TriggerState getTriggerState(final TriggerKey triggerKey) {
    try {
      return this.scheduler.getTriggerState(triggerKey);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ksoot.scheduler.domain.service.SchedulerService#getAllTriggers(org.
   * quartz.TriggerKey[])
   */
  @Override
  public List<Trigger> getAllTriggers(final TriggerKey... triggerKeys) {
    return Arrays.stream(triggerKeys).map(this::getTrigger).toList();
  }

  /*
   * (non-Javadoc)
   *
   * @see com.ksoot.scheduler.domain.service.SchedulerService#getAllTriggers(org.
   * quartz.JobKey)
   */
  @Override
  public List<Trigger> getAllTriggers(final JobKey jobKey) {
    try {
      return (List<Trigger>) this.scheduler.getTriggersOfJob(jobKey).stream().toList();
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#getAllTriggers(org.quartz.impl.matchers.GroupMatcher)
   */
  @Override
  public List<Trigger> getAllTriggers(GroupMatcher<TriggerKey> triggerKeyMatcher) {
    try {
      return this.scheduler.getTriggerKeys(triggerKeyMatcher).stream()
          .map(this::getTrigger)
          .toList();
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  /* (non-Javadoc)
   * @see com.ksoot.scheduler.domain.service.SchedulerService#recoverErrorTriggers()
   */
  @Override
  public List<TriggerKey> recoverErrorTriggers() {
    try {
      return this.scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup()).stream()
          .filter(
              triggerKey -> {
                try {
                  if (this.scheduler.getTriggerState(triggerKey) == TriggerState.ERROR) {
                    this.scheduler.resetTriggerFromErrorState(triggerKey);
                    log.info(
                        "Tigger with key: {} recovered from Error state successfully", triggerKey);
                    return true;
                  } else {
                    return false;
                  }
                } catch (final SchedulerException e) {
                  log.error(
                      "Tigger with key: " + triggerKey + " could not be recovered from Error state",
                      e);
                  return false;
                }
              })
          .toList();

    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  @Override
  public Boolean addCalendar(
      final String name, final Calendar calendar, boolean replace, boolean updateTriggers) {
    try {
      this.scheduler.addCalendar(name, calendar, replace, updateTriggers);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
    return true;
  }

  @Override
  public Boolean deleteCalendar(final String name) {
    try {
      return this.scheduler.deleteCalendar(name);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  @Override
  public Calendar getCalendar(final String name) {
    try {
      return this.scheduler.getCalendar(name);
    } catch (final SchedulerException e) {
      throw new com.ksoot.scheduler.common.SchedulerException(e);
    }
  }

  public List<Calendar> getCalendars() throws SchedulerException {
    return this.scheduler.getCalendarNames().stream().map(this::getCalendar).toList();
  }
}
