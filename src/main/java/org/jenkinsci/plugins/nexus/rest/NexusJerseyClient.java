package org.jenkinsci.plugins.nexus.rest;

import org.sonatype.nexus.rest.model.ScheduledServiceListResourceResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NexusJerseyClient implements NexusClient {

	private String url;

	private String user;

	private String password;

	private Client client;

	private String baseUri;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public NexusJerseyClient(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean ping() {
		init();

		try {
			ScheduledServiceListResourceResponse serviceList = client.resource(baseUri + "schedules").get(
					ScheduledServiceListResourceResponse.class);
			if (serviceList != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init() {
		this.url = url.trim();
		if (!url.matches(".*/$")) {
			url = url + "/";
		}
		this.baseUri = url + "service/local/";
		client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(user, password));

	}

	@Override
	public <T> T get(String resourceName, Class<T> clazz) {
		return client.resource(baseUri + resourceName).get(clazz);
	}

}
