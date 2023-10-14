package com.ksoot.scheduler.web;

import java.util.Map;
import org.quartz.TriggerKey;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class TriggerKeyHandlerMethodArgumentResolver extends KeyHandlerMethodArgumentResolverSupport
    implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    return TriggerKey.class.equals(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    Map<String, String[]> queryParams = webRequest.getParameterMap();
    return TriggerKey.triggerKey(super.getName(queryParams), super.getGroup(queryParams));
  }
}
