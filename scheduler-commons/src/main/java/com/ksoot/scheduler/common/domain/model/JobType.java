package com.ksoot.scheduler.common.domain.model;

public enum JobType {

	SERVICE_CALL(""), EVENT_FIRE("");
	
    private final String description;

    private JobType(final String description) {
        this.description = description;
    }

    public String description() {
    	return this.description;
    }
}
