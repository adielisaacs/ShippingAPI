package com.shipping.api.service;

import java.util.List;

import com.shipping.api.modal.ShipmentCurrentEvents;
import com.shipping.api.modal.ShipmentEvents;

public interface ShipmentStateService {
	
    boolean applyEvent(ShipmentEvents event);

	List<ShipmentCurrentEvents> getEventHistory(String shipmentId);
}
