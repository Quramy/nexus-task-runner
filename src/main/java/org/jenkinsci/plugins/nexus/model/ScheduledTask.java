package org.jenkinsci.plugins.nexus.model;

public class ScheduledTask {
	private boolean enabled;
    private String id;
    private String typeId;
    private String alertEmail;
    private String name;
    private String schedule;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getAlertEmail() {
		return alertEmail;
	}
	public void setAlertEmail(String alertEmail) {
		this.alertEmail = alertEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
}
