package com.ksoot.scheduler.common.quartz.jackson;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;
import org.quartz.Trigger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

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
	public void serialize(final Trigger trigger, final JsonGenerator gen, 
			final SerializerProvider serializers) throws IOException {
		gen.writeBinary(SerializationUtils.serialize(trigger));
	}
}
