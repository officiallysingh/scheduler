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

package com.ksoot.scheduler.common.domain.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * @author Rajveer Singh
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
// @AllArgsConstructor(access = AccessLevel.PRIVATE)
// @AllArgsConstructor(
//    staticName = "of",
//    onConstructor_ = {@JsonCreator})
@EqualsAndHashCode
public class Identity {

  @NotEmpty private String group;

  @NotEmpty private String name;

  //	@JsonCreator
  //    public static Identity of(@JsonProperty("group") final String group,
  //    		@JsonProperty("name") final String name) {
  //    	return new Identity(group, name);
  //    }

  public JobKey jobKey() {
    return JobKey.jobKey(this.name, this.group);
  }

  public TriggerKey triggerKey() {
    return TriggerKey.triggerKey(this.name, this.group);
  }
}
