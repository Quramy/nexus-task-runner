package org.jenkinsci.plugins.nexus.rest;

public class NexusExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NexusExeption() {
		super();
	}

	public NexusExeption(Throwable e) {
		super(e);
	}

	public NexusExeption(String message, Throwable e) {
		super(message, e);
	}
}
