package com.shipping.api.service;

import com.shipping.api.repository.ShipmentEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.api.dto.ShipmentEventRequest;
import com.shipping.api.dto.ShipmentEventResponse;
import com.shipping.api.modal.ProcessingResult;
import com.shipping.api.modal.ShipmentCurrentEvents;
import com.shipping.api.modal.ShipmentEvents;
import com.shipping.api.service.ShipmentStateServiceImpl;
import com.shipping.api.service.ShipmentStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipmentEventServiceImpl implements ShipmentEventService{

	@Autowired
    ShipmentEventRepository eventRepository;
	@Autowired
    ShipmentStateService stateService;
	@Autowired
    JdbcTemplate jdbcTemplate;
	
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Transactional
    public ShipmentEventResponse ingest(ShipmentEventRequest request) {
    	
    	ShipmentEventResponse eventResponse = new ShipmentEventResponse();
        ShipmentEvents event = mapEventRequestToPojo(request);

        try {
        	event.setProcessingResult(ProcessingResult.ACCEPTED);
            ShipmentEvents saved =
                    eventRepository.save(event);

            
            eventResponse.setResult(event.getProcessingResult().toString());
        	eventResponse.setStateChanged(event.getStatus().toString());
        	eventResponse.setShipmentId(event.getShipmentId());
        	eventResponse.setCurrentStatus(saved.getStatus());
            
            boolean changed =
                    stateService.applyEvent(event);
            
            if(changed) {
            	eventResponse.setResult(ProcessingResult.ACCEPTED_OUT_OF_ORDER.toString());
            	
            	return eventResponse;
            }
        	
            return eventResponse;

        } catch (DataIntegrityViolationException ex) {

        	eventResponse.setResult(ProcessingResult.DUPLICATE_REJECTED.toString());
        	eventResponse.setStateChanged(event.getStatus().toString());
        	eventResponse.setShipmentId(event.getShipmentId());
        	
        	return eventResponse;
        }
    }


	private ShipmentEvents mapEventRequestToPojo(ShipmentEventRequest request) {
		// TODO Auto-generated method stub
		ShipmentEvents shipmentEvents = new ShipmentEvents();
		
		//shipmentEvents.setId(request.get)
		shipmentEvents.setEventId(request.getEventId());
		shipmentEvents.setPartner(request.getPartner());
		shipmentEvents.setShipmentId(request.getShipmentId());
		shipmentEvents.setStatus(request.getStatus());
		shipmentEvents.setOccurredAt(request.getOccurredAt());
		shipmentEvents.setReceivedAt(request.getReceivedAt());
		shipmentEvents.setLocation(request.getLocation());
		try {
			String payload = objectMapper.writeValueAsString(request);
			shipmentEvents.setPayloadJson(payload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shipmentEvents.setCreatedAt(request.getOccurredAt());
		
		return shipmentEvents;
	}
    public List<ShipmentEvents> retrieveShipment(String shipmentId) {
    	List<ShipmentEvents> result = jdbcTemplate.query("select * from shipment_events where shipment_id= ?", new Object[] {
    			shipmentId
		        },
		        new BeanPropertyRowMapper <ShipmentEvents> (ShipmentEvents.class));
		return result;

    }
}