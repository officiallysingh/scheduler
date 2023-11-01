package com.ksoot.scheduler.common.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.quartz.JobDetail;
import org.quartz.Trigger;

@Builder
@Valid
public record ScheduleJobRQ(@NotNull JobDetail jobDetail, @NotEmpty Trigger[] triggers) {}
