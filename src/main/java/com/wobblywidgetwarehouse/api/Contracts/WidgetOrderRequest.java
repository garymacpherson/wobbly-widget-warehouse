package com.wobblywidgetwarehouse.api.Contracts;

enum WidgetType {
    SMALL,
    MEDIUM,
    LARGE
}

public class WidgetOrderRequest {
    private WidgetType type;
	private String specialRequest;

	public WidgetType getType() {
		return type;
	}

	public void setType(WidgetType type) {
		this.type = type;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
}