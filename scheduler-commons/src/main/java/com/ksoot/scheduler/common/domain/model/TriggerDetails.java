// package com.ksoot.scheduler.common.domain.model;
//
// import com.ksoot.scheduler.common.Frequency;
// import java.time.Duration;
// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.Date;
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.experimental.Accessors;
// import org.quartz.Trigger;
// import org.quartz.Trigger.TriggerState;
//
// @Getter
// @Accessors(chain = true, fluent = true)
// @AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
// public class TriggerDetails {
//
//  private OffsetDateTime previousFireTime;
//
//  private OffsetDateTime nextFireTime;
//
//  private Frequency repeatInterval;
//
//  private TriggerState triggerState;
//
//  public static TriggerDetails of(
//      final Trigger trigger, final TriggerState triggerState, final Duration repeatInterval) {
//    Date fireDate = trigger.getPreviousFireTime();
//    Date nextFireDate = trigger.getNextFireTime();
//    OffsetDateTime fireTime =
//        fireDate == null ? null : fireDate.toInstant().atOffset(ZoneOffset.UTC);
//    OffsetDateTime nextFireTime =
//        nextFireDate == null ? null : nextFireDate.toInstant().atOffset(ZoneOffset.UTC);
//    //        Duration repeatInterval = AbstractSchedulerService.getJobFrequency(trigger);
//    return of(
//        fireTime,
//        nextFireTime,
//        repeatInterval == null ? null : Frequency.of(repeatInterval),
//        triggerState);
//  }
// }
