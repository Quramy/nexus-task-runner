package org.jenkinsci.plugins.nexus.rest.schedules;

import org.jenkinsci.plugins.nexus.rest.NexusClient;
import org.sonatype.nexus.rest.model.ScheduledServiceListResource;
import org.sonatype.nexus.rest.model.ScheduledServiceResourceStatus;
import org.sonatype.nexus.rest.model.ScheduledServiceResourceStatusResponse;

/**
 * This class provides methods for executing Nexus scheduled task.
 * 
 * @author yosuke
 * 
 */
public class TaskRunner {

	private static final String STATUS_RUNNABLE = "Waiting";
	private static final String STATUS_ACCEPTED_SUBMITTED = "SUBMITTED";
	private static final String STATUS_ACCEPTED_RUNNING = "RUNNING";

	private NexusClient client;
	private TaskUtil taskUtil;

	public TaskRunner(NexusClient client) {
		this.client = client;
		taskUtil = new TaskUtil(client);
	}

	public TaskStatus exec(String id) {
		// check status.
		ScheduledServiceListResource task = taskUtil.fetchTask(id);
		if (!task.getReadableStatus().equals(STATUS_RUNNABLE)) {
			if (task.getStatus().equals(STATUS_ACCEPTED_RUNNING)) {
				return TaskStatus.WARNING_STILL_RUNNING;
			}
			return TaskStatus.NG;
		}

		Long lastRun = task.getLastRunTimeInMillis();
		if (lastRun == null) {
			lastRun = 0l;
		}

		ScheduledServiceResourceStatus status = client.get("schedule_run/" + id,
				ScheduledServiceResourceStatusResponse.class).getData();

		if (status.getStatus().equals(STATUS_ACCEPTED_SUBMITTED)) {
			if (taskUtil.fetchTask(id).getLastRunTimeInMillis() > lastRun) {
				return TaskStatus.OK_DONE;
			} else {
				return TaskStatus.NG_UNKNOWN;
			}
		} else if (status.getStatus().equals(STATUS_ACCEPTED_RUNNING)) {
			return TaskStatus.OK_ACCEPTED;
		} else {
			return TaskStatus.NG;
		}
	}

}
