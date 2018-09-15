package com.nm.model;

import java.io.Serializable;
import java.util.UUID;

public class Cat implements Serializable {

	private static final long serialVersionUID = -6359913833007673082L;
	private String file;
	private String id = UUID.randomUUID().toString();

	public String getId() {
		return id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
