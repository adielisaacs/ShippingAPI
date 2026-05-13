package com.shipping.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.shipping.api.modal.ShipmentStatus;

/**
 * @author user
 *
 */
public class ShipmentEventResponse {

    @NotBlank
    private String result;

    @NotBlank
    private String stateChanged;

    @NotBlank
    private String shipmentId;

    @NotNull
    private ShipmentStatus currentStatus;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStateChanged() {
		return stateChanged;
	}

	public void setStateChanged(String stateChanged) {
		this.stateChanged = stateChanged;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public ShipmentStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(ShipmentStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

}