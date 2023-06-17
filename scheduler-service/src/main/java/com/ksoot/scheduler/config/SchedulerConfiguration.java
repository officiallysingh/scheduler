package com.ksoot.scheduler.config;

import com.ksoot.scheduler.util.ExternalFileLoaderUtil;
import com.ksoot.scheduler.web.JobKeyHandlerMethodArgumentResolver;
import com.ksoot.scheduler.web.TriggerKeyHandlerMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

import static org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME;

@Slf4j
@Configuration
public class SchedulerConfiguration {

    private static final String QUARTZ_PROPERTIES = "quartz/quartz.properties";

    @Bean
    public SpringBeanJobFactory jobFactory(final ApplicationContext applicationContext) {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(final ApplicationContext applicationContext,
                                                 final SpringBeanJobFactory jobFactory,
                                                 final DataSource dataSource,
                                                 @Qualifier(APPLICATION_TASK_EXECUTOR_BEAN_NAME) Executor taskExecutor,
                                                 final QuartzProperties quartzProperties) {
        log.info("Starting to initialize SchedulerFactoryBean ...");
//        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(quartzProperties.isOverwriteExistingJobs());
        factory.setAutoStartup(quartzProperties.isAutoStartup());
        factory.setWaitForJobsToCompleteOnShutdown(quartzProperties.isWaitForJobsToCompleteOnShutdown());
        factory.setStartupDelay((int) quartzProperties.getStartupDelay().getSeconds());
        factory.setDataSource(dataSource);
        factory.setTaskExecutor(taskExecutor);
        factory.setJobFactory(jobFactory);

//        factory.setConfigLocation(new ClassPathResource(path));

        Properties quartzProps = new Properties();
        try {
            Resource quartzFile = new ClassPathResource(QUARTZ_PROPERTIES);
            if (quartzFile.exists()) {
                quartzProps.putAll(ExternalFileLoaderUtil.loadProperties(quartzFile));
                log.info("Quartz property file: " + QUARTZ_PROPERTIES + " added");
            } else {
                log.warn("Quartz property file: " + QUARTZ_PROPERTIES + " does not exist");
            }
        } catch (IOException e) {
            log.error("Exception while reading default properties Quartz file: " + QUARTZ_PROPERTIES, e);
        }

        quartzProps.putAll(quartzProperties.getProperties());
        factory.setQuartzProperties(quartzProps);

        return factory;
    }

    @Bean
    public Scheduler scheduler(final SchedulerFactoryBean schedulerFactory) {
        return schedulerFactory.getScheduler();
    }

    @Bean
    public TriggerKeyHandlerMethodArgumentResolver triggerKeyHandlerMethodArgumentResolver() {
        return new TriggerKeyHandlerMethodArgumentResolver();
    }

    @Bean
    public JobKeyHandlerMethodArgumentResolver jobKeyHandlerMethodArgumentResolver() {
        return new JobKeyHandlerMethodArgumentResolver();
    }
}