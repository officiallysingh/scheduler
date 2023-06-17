package com.ksoot.scheduler.common.quartz.jackson;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;
import org.quartz.JobDetail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

class JobDetailDeserializer extends JsonDeserializer<JobDetail> {

	/*
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public JobDetail deserialize(final JsonParser parser, final DeserializationContext ctxt) 
			throws IOException {
		return SerializationUtils.deserialize(parser.getBinaryValue());
	}
}
