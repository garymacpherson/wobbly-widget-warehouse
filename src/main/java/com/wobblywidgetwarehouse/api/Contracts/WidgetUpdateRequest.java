package com.wobblywidgetwarehouse.api.contracts;

public class WidgetUpdateRequest {
	private String serialisedObject;

	public String getSerialisedObject() {
		return serialisedObject;
	}

	public void setSerialisedObject(String serialisedObject) {
		this.serialisedObject = serialisedObject;
	}
}