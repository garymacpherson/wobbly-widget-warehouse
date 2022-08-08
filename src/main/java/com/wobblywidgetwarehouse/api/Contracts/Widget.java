package com.wobblywidgetwarehouse.api.contracts;

import java.io.Serializable;
import java.util.UUID;

public class Widget implements Serializable {
    private final static long serialVersionUID = 1;

	private UUID id;
	private WidgetType type;

	public Widget() {
		
	}

	public Widget(UUID id, WidgetType type) {
		this.id = id;
		this.type = type;
	}

	public Widget(WidgetType type) {
		this.id = UUID.randomUUID();
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public WidgetType getType() {
		return type;
	}

	public void setType(WidgetType type) {
		this.type = type;
	}
}