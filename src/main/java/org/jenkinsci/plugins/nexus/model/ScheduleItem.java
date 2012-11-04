package org.jenkinsci.plugins.nexus.model;

public class ScheduleItem {
	
	private String resourceURI;
	private boolean enabled;
	private String name;
	private String id;
	private String typeId;
	private String typeName;
	private String status;
	private String readableStatus;
	private String schedule;
	private String lastRunTime;
	private String lastRunResult;
	private Long lastRunTimeInMilis;
	private String created;
	private Long createdInMilis;
	private String nextRunTime;
	public String getResourceURI() {
		return resourceURI;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReadableStatus() {
		return readableStatus;
	}
	public void setReadableStatus(String readableStatus) {
		this.readableStatus = readableStatus;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getLastRunTime() {
		return lastRunTime;
	}
	public void setLastRunTime(String lastRunTime) {
		this.lastRunTime = lastRunTime;
	}
	public String getLastRunResult() {
		return lastRunResult;
	}
	public void setLastRunResult(String lastRunResult) {
		this.lastRunResult = lastRunResult;
	}
	public Long getLastRunTimeInMilis() {
		return lastRunTimeInMilis;
	}
	public void setLastRunTimeInMilis(Long lastRunTimeInMilis) {
		this.lastRunTimeInMilis = lastRunTimeInMilis;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Long getCreatedInMilis() {
		return createdInMilis;
	}
	public void setCreatedInMilis(Long createdInMilis) {
		this.createdInMilis = createdInMilis;
	}
	public String getNextRunTime() {
		return nextRunTime;
	}
	public void setNextRunTime(String nextRunTime) {
		this.nextRunTime = nextRunTime;
	}
}
