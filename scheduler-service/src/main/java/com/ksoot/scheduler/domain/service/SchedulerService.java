package com.ksoot.scheduler.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;

public interface SchedulerService {

  // ------------- Jobs -------------
  /**
   * Determine whether a Job with the given identifier already exists within the scheduler.
   *
   * @param jobKey <code>JobKey</code>
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if job exists or not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> if there is an internal Scheduler error
   * @see Scheduler#checkExists(JobKey jobKey)
   */
  Boolean doesJobExist(final JobKey jobKey);

  /**
   * Get all <code>JobDetail</code>s matching given <code>JobKey</code> <code>GroupMatcher</code>
   *
   * @param jobKeyMatcher {@link GroupMatcher} The <code>Trigger</code> group matcher
   * @return {@code reactor.core.publisher.Flux<Trigger>} All <code>JobDetail</code>s matching the
   *     given criteria
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#getJobKeys(GroupMatcher matcher)
   */
  List<JobDetail> getAllJobs(final GroupMatcher<JobKey> jobKeyMatcher);

  /**
   * Add the given Job to the Scheduler - with no associated <code>Trigger</code>. The Job will be
   * 'dormant' until it is scheduled with a <code>Trigger</code>, or <code>Scheduler.triggerJob()
   * </code> is called for it. The Job must by definition be 'durable', if it is not,
   * SchedulerException will be thrown.
   *
   * @param jobDetail <code>JobDetail</code> instance
   * @return <code>Boolean</code> as <code>Mono</code>
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> if there is an internal Scheduler error, or if the Job is not
   *     durable, or a Job with the same name already exists, and replace is false.
   * @see Scheduler#addJob(JobDetail jobDetail, boolean replace)
   */
  Boolean addJob(final JobDetail jobDetail);

  /**
   * Add the new job to the scheduler, instructing it to "replace" the existing job with the given
   * name and group (if any)
   *
   * @param jobDetail JobDetail instance
   * @return <code>Boolean</code> as <code>Mono</code> if Job updated successfully otherwise
   *     exception is thrown
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> if there is an internal Scheduler error, or if the Job is not
   *     durable, or a Job with the same name already exists, and replace is false.
   * @see Scheduler#addJob(JobDetail jobDetail, boolean replace)
   */
  Boolean updateJob(final JobDetail jobDetail);

  /**
   * Get the <code>JobDetail</code> for the Job instance with the given key. The returned <code>
   * JobDetail</code> object will be a snap-shot of the actual stored JobDetail. If you wish to
   * modify the JobDetail, you must re-store the JobDetail afterward (e.g. see <code>
   * addJob(JobDetail, boolean))</code>.
   *
   * @param jobKey <code>JobKey</code>
   * @return {@code Mono<JobDetail>} or empty <b>Mono</b> if the Job was found or not found
   *     respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#getJobDetail(JobKey jobKey)
   */
  JobDetail getJobDetail(final JobKey jobKey);

  /**
   * Delete the identified Job from the Scheduler - and any associated Triggers.
   *
   * @param jobKey <code>JobKey</code>
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if the Job was found and deleted or
   *     not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#deleteJob(JobKey jobKey)
   */
  Boolean deleteJob(final JobKey jobKey);

  /**
   * Delete the identified Job from the Scheduler - and any associated Triggers.
   *
   * @param jobKeys <code>Array</code> of <code>JobKey</code>s
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if all of the Jobs were found and
   *     deleted or if one or more were not deleted respectively.
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#deleteJob(JobKey jobKey)
   */
  Boolean deleteJobs(final JobKey... jobKeys);

  /**
   * Add the given <code>org.quartz.JobDetail</code> to the Scheduler, and associate the given
   * <code>Trigger</code> with it. If the given Trigger does not reference any Job, then it will be
   * set to reference the Job passed with it into this method.
   *
   * @param jobDetail <code>JobDetail</code> instance
   * @param trigger <code>Trigger</code> to schedule the job with
   * @return The first fire time as {@code Mono<OffsetDateTime>} of the newly scheduled trigger or
   *     Empty <code>Mono</code> if Job could not be scheduled.
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If the Job or Trigger cannot be added to the Scheduler, or there
   *     is an internal Scheduler error.
   * @see Scheduler#scheduleJob(JobDetail jobDetail, Trigger trigger)
   */
  OffsetDateTime scheduleJob(final JobDetail jobDetail, final Trigger trigger);

  /**
   * Schedule the given job with the related set of triggers. If any of the given job or triggers
   * already exist (or more specifically, if the keys are not unique) and the replace parameter is
   * not set to true then an exception will be thrown.
   *
   * @param replace Whether to replace if trigger with same key already exists.
   * @param jobDetail <code>JobDetail</code> instance
   * @param triggers Array of <code>Trigger</code>s to schedule the job with
   * @return <code>Boolean</code> as <code>Mono</code> if Job scheduled successfully otherwise
   *     exception is thrown
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If replace parameter is set to false and some trigger already
   *     exists with duplicate key.
   * @see Scheduler#scheduleJob(JobDetail, Set, boolean)
   */
  Boolean scheduleJob(final JobDetail jobDetail, boolean replace, final Trigger... triggers);

  /**
   * Remove (delete) the <code>org.quartz.Trigger</code> with the given key, and store the new given
   * one - which must be associated with the same job (the new trigger must have the job name &
   * group specified). The new trigger must have the same <code>TriggerKey</code> as the old
   * trigger.
   *
   * @param newTrigger New <code>Trigger</code> to schedule the job with
   * @return Empty <code>Mono</code> if a <code>Trigger</code> with the given name & group was not
   *     found and removed from the store (and the new trigger is therefore not stored), otherwise
   *     the first fire time as {@code Mono<OffsetDateTime>} of the newly scheduled trigger is
   *     returned.
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#rescheduleJob(TriggerKey triggerKey, Trigger newTrigger)
   */
  OffsetDateTime reScheduleJob(final Trigger newTrigger);

  /**
   * Remove (delete) the <code>org.quartz.Trigger</code> with the given key, and store the new given
   * one - which must be associated with the same job (the new trigger must have the job name &
   * group specified) - however, the new trigger need not have the same name as the old trigger.
   *
   * @param oldTriggerKey Old <code>TriggerKey</code>
   * @param newTrigger New <code>Trigger</code> to schedule the job with
   * @return Empty <code>Mono</code> if a Trigger with the given name & group was not found and
   *     removed from the store (and the new trigger is therefore not stored), otherwise the first
   *     fire time as {@code Mono<OffsetDateTime>} of the newly scheduled trigger is returned.
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#rescheduleJob(TriggerKey triggerKey, Trigger newTrigger)
   */
  OffsetDateTime reScheduleJob(final TriggerKey oldTriggerKey, final Trigger newTrigger);

  /**
   * Remove the indicated <code>Trigger</code> from the scheduler. If the related job does not have
   * any other triggers, and the job is not durable, then the job will also be deleted.
   *
   * @param triggerKey <code>TriggerKey</code> of the <code>Trigger</code> to be unscheduled.
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if <code>Trigger</code> is unscheduled
   *     successfully or not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#unscheduleJob(TriggerKey triggerKey)
   */
  Boolean unScheduleJob(final TriggerKey triggerKey);

  /**
   * Remove all of the indicated <code>Trigger</code>s from the scheduler. If the related job does
   * not have any other triggers, and the job is not durable, then the job will also be deleted.
   * Note that while this bulk operation is likely more efficient than invoking {@link
   * Scheduler#unscheduleJob(TriggerKey triggerKey)} several times, it may have the adverse affect
   * of holding data locks for a single long duration of time (rather than lots of small durations
   * of time).
   *
   * @param triggerKeys <code>TriggerKey</code>s
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if all <code>Trigger</code>s are
   *     unscheduled successfully or not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#unscheduleJobs(List triggerKeys)
   */
  Boolean unScheduleJobs(final TriggerKey... triggerKeys);

  // ------------- Trigger -------------
  /**
   * Determine whether a <code>Trigger</code> with the given identifier already exists within the
   * scheduler.
   *
   * @param triggerKey <code>TriggerKey</code> if a Trigger exists with the given identifier
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if a <code>Trigger</code> exists with
   *     the given identifier or not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#checkExists(TriggerKey triggerKey)
   */
  Boolean doesTriggerExist(final TriggerKey triggerKey);

  /**
   * Though there is no direct way to update a <code>Trigger</code>. Effectively the update <code>
   * Trigger</code> is achieved by {@link #reScheduleJob}
   *
   * @param newTrigger New <code>Trigger</code> to schedule the job with
   * @return Empty <code>Mono</code> if a Trigger with the given name & group was not found and
   *     removed from the store (and the new trigger is therefore not stored), otherwise the first
   *     fire time as {@code Mono<OffsetDateTime>} of the newly scheduled trigger is returned.
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see #reScheduleJob(Trigger newTrigger)
   */
  OffsetDateTime updateTrigger(final Trigger newTrigger);

  /**
   * Though there is no direct way to delete a <code>Trigger</code>. Effectively the delete <code>
   * Trigger</code> is achieved by {@link #unScheduleJob(TriggerKey) }
   *
   * @param triggerKey <code>TriggerKey</code> of the <code>Trigger</code> to be deleted.
   * @return <b>true</b> or <b>false</b> as <code>Mono</code> if <code>Trigger</code> is unscheduled
   *     successfully or not respectively
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see #unScheduleJob(TriggerKey triggerKey)
   */
  Boolean deleteTrigger(final TriggerKey triggerKey);

  /**
   * Get the <code>Trigger</code> instance with the given key. The returned <code>Trigger</code>
   * object will be a snap-shot of the actual stored trigger. If you wish to modify the trigger, you
   * must re-store the trigger afterward (e.g. see {@link #reScheduleJob(TriggerKey, Trigger) }.
   *
   * @param triggerKey <code>TriggerKey</code> of the <code>Trigger</code>
   * @return Empty <code>Mono</code> if a <code>Trigger</code> with the given key was not found
   *     otherwise {@code Mono<Trigger>}
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#getTriggerState(TriggerKey triggerKey)
   */
  Trigger getTrigger(final TriggerKey triggerKey);

  /**
   * Get the current state of the identified <code>{@link Trigger}</code>.
   *
   * @param triggerKey <code>TriggerKey</code> of the <code>Trigger</code>
   * @return Empty <code>Mono</code> if a <code>Trigger</code> with the given key was not found
   *     otherwise {@code Mono<TriggerState>}
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see TriggerState
   * @see Scheduler#checkExists(JobKey jobKey)
   */
  TriggerState getTriggerState(final TriggerKey triggerKey);

  /**
   * Get all <code>Trigger</code>s identified by given <code>TriggerKey</code>s
   *
   * @param triggerKeys <TriggerKey>s
   * @return {@code reactor.core.publisher.Flux<Trigger>} All <code>Trigger</code>s for given
   *     code>TriggerKey</code>s
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see #getTrigger(TriggerKey triggerKey)
   */
  List<Trigger> getAllTriggers(final TriggerKey... triggerKeys);

  /**
   * Get all <code>Trigger</code>s identified by given <code>JobKey</code>
   *
   * @param jobKey <code>JobKey</code>
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @return {@code reactor.core.publisher.Flux<Trigger>} All <code>Trigger</code>s for given <code>
   *     JobKey</code>
   * @see Scheduler#getTriggersOfJob(JobKey jobKey)
   */
  List<Trigger> getAllTriggers(final JobKey jobKey);

  /**
   * Get all the <code>Trigger</code>s matching given <code>Trigger</code> <code>GroupMatcher</code>
   *
   * @param triggerKeyMatcher {@link GroupMatcher} The <code>Trigger</code> group matcher
   * @return {@code reactor.core.publisher.Flux<Trigger>} All <code>Trigger</code>s matching the
   *     given criteria
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#getTriggerKeys(GroupMatcher matcher)
   */
  List<Trigger> getAllTriggers(final GroupMatcher<TriggerKey> triggerKeyMatcher);

  /**
   * Iterate through all the triggers in the scheduler. For each Trigger, reset the current state of
   * the identified <code>{@link Trigger}</code> from {@link TriggerState#ERROR} to {@link
   * TriggerState#NORMAL} or {@link TriggerState#PAUSED} as appropriate.
   *
   * <p>Only affects triggers that are in ERROR state - if identified trigger is not in that state
   * then the result is a no-op.
   *
   * <p>The result will be the trigger returning to the normal, waiting to be fired state, unless
   * the trigger's group has been paused, in which case it will go into the PAUSED state.
   *
   * @return {@code reactor.core.publisher.Flux<TriggerKey>} The <code>TriggerKey</code>s which were
   *     in error state and recovered
   * @throws com.ksoot.scheduler.common.SchedulerException with error resolver <code>
   *     SCHEDULER_EXCEPTION</code> If there is an internal Scheduler error.
   * @see Scheduler#getTriggerKeys(GroupMatcher matcher)
   * @see Scheduler#getTriggerState(TriggerKey triggerKey)
   * @see Scheduler#resetTriggerFromErrorState(TriggerKey triggerKey)
   */
  List<TriggerKey> recoverErrorTriggers();

  Boolean addCalendar(
      String name, final Calendar calendar, boolean replace, boolean updateTriggers);

  Boolean deleteCalendar(final String name);

  Calendar getCalendar(final String name);
}
