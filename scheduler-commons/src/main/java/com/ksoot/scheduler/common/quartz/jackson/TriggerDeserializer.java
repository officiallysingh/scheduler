package com.ksoot.scheduler.common.quartz.jackson;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;
import org.quartz.Trigger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

class TriggerDeserializer extends JsonDeserializer<Trigger> {

	/*
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Trigger deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
		return SerializationUtils.deserialize(parser.getBinaryValue());
	}
}
