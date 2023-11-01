package com.ksoot.scheduler.common.quartz.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.apache.commons.lang3.SerializationUtils;
import org.quartz.Trigger;

class TriggerSerializer extends JsonSerializer<Trigger> {

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
      final Trigger trigger, final JsonGenerator gen, final SerializerProvider provider)
      throws IOException {
    gen.writeBinary(SerializationUtils.serialize(trigger));
  }
}
