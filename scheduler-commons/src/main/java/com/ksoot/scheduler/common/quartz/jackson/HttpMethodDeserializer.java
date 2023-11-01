package com.ksoot.scheduler.common.quartz.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.springframework.http.HttpMethod;

final class HttpMethodDeserializer extends JsonDeserializer<HttpMethod> {

  HttpMethodDeserializer() {}

  @Override
  public HttpMethod deserialize(final JsonParser json, final DeserializationContext context)
      throws IOException {
    final String method = json.getValueAsString();
    return HttpMethod.valueOf(method);
  }
}
