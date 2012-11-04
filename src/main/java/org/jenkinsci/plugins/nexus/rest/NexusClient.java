package org.jenkinsci.plugins.nexus.rest;

/**
 * APIs to get Nexus's resources.
 * 
 * @author Yosuke Kurami
 * 
 */
public interface NexusClient {
	boolean ping();

	void init();

	/**
	 * 
	 * @param path
	 *            URI pash for resource you want.
	 * @param clazz
	 * 
	 * @return
	 */
	<T> T get(String path, Class<T> clazz);
}
