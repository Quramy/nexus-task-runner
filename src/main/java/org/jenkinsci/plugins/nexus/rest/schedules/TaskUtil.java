package org.jenkinsci.plugins.nexus.rest.schedules;

import java.util.ArrayList;
import java.util.List;

import org.jenkinsci.plugins.nexus.rest.NexusClient;
import org.sonatype.nexus.rest.model.ScheduledServiceListResource;
import org.sonatype.nexus.rest.model.ScheduledServiceListResourceResponse;

/**
 * Utility class for searching Nexus scheduled task.
 * 
 * @author Yosuke Kurami
 * 
 */
public class TaskUtil {

	private NexusClient client;

	public TaskUtil(NexusClient client) {
		this.client = client;
	}

	private ScheduledServiceListResourceResponse cache;

	/**
	 * Fetch list of tasks using Nexus REST API.
	 * 
	 * @param name
	 *            Scheduled task's name.
	 * @return list of tasks whose name matches the argument.
	 */
	public List<ScheduledServiceListResource> fetchTasks(String name) {
		ScheduledServiceListResourceResponse allTask = client.get("schedules",
				ScheduledServiceListResourceResponse.class);
		if (allTask == null || allTask.getData() == null || allTask.getData().size() == 0) {
			return null;
		}

		List<ScheduledServiceListResource> matchedList = new ArrayList<ScheduledServiceListResource>();
		List<ScheduledServiceListResource> taskList = allTask.getData();
		for (ScheduledServiceListResource task : taskList) {
			if (task.getName().matches(name)) {
				matchedList.add(task);
			}
		}
		return matchedList;
	}

	/**
	 * Fetch list of tasks.
	 * If there is cache, this method uses it without executing REST API.
	 * 
	 * @param name
	 *            Scheduled task's name.
	 * @return list of tasks whose name matches the argument.
	 */
	public List<ScheduledServiceListResource> fastFetchTasks(String name) {
		if (cache == null) {
			fetchAll();
		}
		if (cache == null || cache.getData() == null || cache.getData().size() == 0) {
			return null;
		}

		List<ScheduledServiceListResource> matchedList = new ArrayList<ScheduledServiceListResource>();
		List<ScheduledServiceListResource> taskList = cache.getData();
		for (ScheduledServiceListResource task : taskList) {
			if (task.getName().matches(name)) {
				matchedList.add(task);
			}
		}
		return matchedList;
	}

	/**
	 * Fetch all tasks, and refresh cache.
	 * @return
	 */
	public List<ScheduledServiceListResource> fetchAll() {
		cache = client.get("schedules", ScheduledServiceListResourceResponse.class);
		return cache == null ? null : cache.getData();
	}

	/**
	 * Fetch one task whose id matches the argument.
	 * @param id
	 * @return
	 */
	public ScheduledServiceListResource fetchTask(String id) {

		ScheduledServiceListResourceResponse allTask = client.get("schedules",
				ScheduledServiceListResourceResponse.class);
		if (allTask == null || allTask.getData() == null || allTask.getData().size() == 0) {
			return null;
		}

		List<ScheduledServiceListResource> taskList = allTask.getData();
		for (ScheduledServiceListResource task : taskList) {
			if (task.getId().equals(id)) {
				return task;
			}
		}
		return null;

	}
}
