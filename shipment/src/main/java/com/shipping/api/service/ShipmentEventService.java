package com.shipping.api.service;

import java.util.List;

import com.shipping.api.dto.ShipmentEventRequest;
import com.shipping.api.dto.ShipmentEventResponse;
import com.shipping.api.modal.ShipmentEvents;

public interface ShipmentEventService {
	public ShipmentEventResponse ingest(
            ShipmentEventRequest request
    );

	public List<ShipmentEvents> retrieveShipment(String shipmentId);
}
