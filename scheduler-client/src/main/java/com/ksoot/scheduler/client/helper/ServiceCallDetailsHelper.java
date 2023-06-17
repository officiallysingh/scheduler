package com.ksoot.scheduler.client.helper;

public interface ServiceCallDetailsHelper {

//	public void method(final HttpMethod httpMethod);

	public JobDataHelper put(final String url);

	public JobDataHelper post(final String url);
}
