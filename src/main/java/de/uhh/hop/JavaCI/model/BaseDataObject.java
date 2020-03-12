package de.uhh.hop.JavaCI.model;

import java.util.UUID;

public class BaseDataObject
{
	protected String id;

	public BaseDataObject() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return this.id;
	}
}
