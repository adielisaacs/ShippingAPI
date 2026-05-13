package com.shipping.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShipmentStateResponse {

    @NotBlank
    private String shipmentId;

    @NotNull
    private String currentStatus;

    @NotNull
    private Date statusOccurredAt;

    @NotNull
    private Integer eventCount;

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Date getStatusOccurredAt() {
		return statusOccurredAt;
	}

	public void setStatusOccurredAt(Date statusOccurredAt) {
		this.statusOccurredAt = statusOccurredAt;
	}

	public Integer getEventCount() {
		return eventCount;
	}

	public void setEventCount(Integer eventCount) {
		this.eventCount = eventCount;
	}


}