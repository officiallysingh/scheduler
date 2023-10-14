package com.ksoot.scheduler.common.quartz.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.apache.commons.lang3.SerializationUtils;
import org.quartz.JobDetail;

class JobDetailSerializer extends JsonSerializer<JobDetail> {

  /*
   * (non-Javadoc)
   *
   * @see
   * com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
   * com.fasterxml.jackson.core.JsonGenerator,
   * com.fasterxml.jackson.databind.SerializerProvider)
   */
  @Override
  public void serialize(
      final JobDetail jobDetail, final JsonGenerator gen, final SerializerProvider serializers)
      throws IOException {
    gen.writeBinary(SerializationUtils.serialize(jobDetail));
  }
}
