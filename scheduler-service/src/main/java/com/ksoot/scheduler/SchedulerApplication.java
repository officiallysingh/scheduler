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

package com.ksoot.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ConfigurationPropertiesScan("com.ksoot.hammer")
// @ComponentScan(basePackages = { "com.ksoot.hammer.common.spring.config.ex",
// "com.ksoot.hammer.test" })
// @EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.class })
// @ImportResource("classpath:config//external-conf.xml")
// @EnableAsync
// @EnableScheduling
// @EnableRetry
public class SchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchedulerApplication.class, args);
    //		SpringApplication application = new SpringApplication(SchedulerApplication.class);
    //		application.setWebApplicationType(WebApplicationType.REACTIVE);
    //		BootHelper.boot(application, args);
  }
}
