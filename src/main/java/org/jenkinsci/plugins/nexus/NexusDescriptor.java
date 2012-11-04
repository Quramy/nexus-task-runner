package org.jenkinsci.plugins.nexus;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;

import org.jenkinsci.plugins.nexus.rest.NexusClient;
import org.jenkinsci.plugins.nexus.rest.NexusJerseyClient;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

public class NexusDescriptor extends AbstractDescribableImpl<NexusDescriptor> {

	private String name;

	private String url;

	private String user;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	@DataBoundConstructor
	public NexusDescriptor(String name, String url, String user, String password) {
		this.name = name;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Extension
	public static final class DescriptorImpl extends Descriptor<NexusDescriptor> {

		@Override
		public String getDisplayName() {
			return "Nexus";
		}

		public FormValidation doCheckName(@QueryParameter String value) {
			return FormValidation.validateRequired(value);
		}

		public FormValidation doCheckUrl(@QueryParameter String value) {
			return FormValidation.validateRequired(value);
		}

		public FormValidation doCheckUser(@QueryParameter String value) {
			return FormValidation.validateRequired(value);
		}

		public FormValidation doTestConnection(@QueryParameter("url") final String url,
				@QueryParameter("user") final String user, @QueryParameter("password") final String password) {
			NexusClient client = new NexusJerseyClient(url, user, password);
			if (client.ping()) {
				return FormValidation.ok();
			} else {
				return FormValidation.error("failed to connect to " + url);
			}
		}

	}
}
