package com.ksoot.scheduler.common.quartz.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import org.quartz.JobDetail;
import org.quartz.Trigger;

public class QuartzModule extends Module {

  private final SimpleSerializers serializers = new SimpleSerializers();
  private final SimpleDeserializers deserializers = new SimpleDeserializers();

  public QuartzModule() {
    super();
    serializers.addSerializer(Trigger.class, new TriggerSerializer());
    serializers.addSerializer(JobDetail.class, new JobDetailSerializer());
    deserializers.addDeserializer(Trigger.class, new TriggerDeserializer());
    deserializers.addDeserializer(JobDetail.class, new JobDetailDeserializer());
  }

  @Override
  public String getModuleName() {
    return QuartzModule.class.getName();
  }

  @Override
  public Version version() {
    return new Version(0, 0, 1, null, null, null);
  }

  @Override
  public void setupModule(final SetupContext context) {
    context.addSerializers(this.serializers);
    context.addDeserializers(this.deserializers);
  }
}
