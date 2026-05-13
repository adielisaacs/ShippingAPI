package com.shipping.api.controller;

import com.shipping.api.dto.ShipmentEventRequest;
import com.shipping.api.dto.ShipmentEventResponse;
import com.shipping.api.dto.ShipmentHistoryResponse;
import com.shipping.api.dto.ShipmentStateResponse;
import com.shipping.api.modal.ShipmentCurrentEvents;
import com.shipping.api.modal.ShipmentEvents;
import com.shipping.api.modal.ShipmentStatus;
import com.shipping.api.repository.ShipmentEventRepository;
import com.shipping.api.repository.ShipmentStateRepository;
import com.shipping.api.service.ShipmentEventService;
import com.shipping.api.service.ShipmentStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RestController
public class ShipmentEventController {

    @Autowired
    ShipmentStateRepository stateRepository;
    @Autowired
    ShipmentEventRepository eventRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ShipmentEventService eventservice;
    @Autowired
    ShipmentStateService stateservice;
    
    @PostMapping("/shipment-events")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<ShipmentEventResponse> ingest(@RequestBody ShipmentEventRequest  request){

        ShipmentEventResponse response =
        		eventservice.ingest(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/shipments/{shipmentId}")
    public List<ShipmentEvents> getState( @PathVariable String shipmentId){
    	
    	List<ShipmentEvents> result = eventservice.retrieveShipment(shipmentId);
        return result;    
    }
    
    @GetMapping("/shipments/{shipmentId}/events")
    public List<ShipmentCurrentEvents> history(
            @PathVariable String shipmentId
    ) {

    	List<ShipmentCurrentEvents> result = stateservice.getEventHistory(shipmentId);
        return result;  
    }
}
