package com.shipping.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipping.api.repository.ShipmentEventRepository;
import com.shipping.api.repository.ShipmentStateRepository;
import com.shipping.api.modal.ProcessingResult;
import com.shipping.api.modal.ShipmentCurrentEvents;
import com.shipping.api.modal.ShipmentEvents;

import java.util.Date;
import java.util.List;

@Service
public class ShipmentStateServiceImpl implements ShipmentStateService {
	@Autowired
    ShipmentStateRepository stateRepository;
	@Autowired
	ShipmentEventRepository eventRepository;
	@Autowired
    JdbcTemplate jdbcTemplate;
	
    @Transactional
    public boolean applyEvent(ShipmentEvents event) {

        ShipmentCurrentEvents current = getEventById(event.getEventId());

        if (current.getLast_event_id() == null) {

            ShipmentCurrentEvents newCurrentEvent = createNewState(event);
            stateRepository.save(newCurrentEvent);
            event.setProcessingResult(
                    ProcessingResult.ACCEPTED
            );

            eventRepository.save(event);
            return false;
        }else if ((event.getOccurredAt().after(current.getStatus_occurred_at()))&&(event.getEventId() == current.getLast_event_id())) {
        	current.setCurrent_status(ProcessingResult.DUPLICATE_REJECTED.name());
        	current.setState_reason("\"Matching shipment/status/occurredAt/location already processed\"");
            stateRepository.save(current);
            event.setProcessingResult(
                    ProcessingResult.DUPLICATE_REJECTED
            );

            eventRepository.save(event);
            return true;
        }else if (event.getOccurredAt().before(current.getStatus_occurred_at())) {

        	current.setCurrent_status(ProcessingResult.CONFLICT_ACCEPTED.name());
        	current.setState_reason("\"Matching shipment/status/occurredAt/location before time\"");
            
        	stateRepository.save(current);
            event.setProcessingResult(
                    ProcessingResult.CONFLICT_ACCEPTED
            );

            eventRepository.save(event);
            return true;
        }else if ((event.getStatus().name().equals(current.getCurrent_status()))&&(event.getEventId().equals(current.getLast_event_id()))&&(event.getShipmentId().equals(current.getShipment_id()))) {
        	
        	current.setCurrent_status(ProcessingResult.CONFLICT_ACCEPTED.name());
        	current.setState_reason("\"Matching shipment/status/occurredAt/location in in conflict\"");
                        stateRepository.save(current);
            
            event.setProcessingResult(
                    ProcessingResult.CONFLICT_ACCEPTED
            );

            eventRepository.save(event);
            return true;
        }

        return false;
    }

	private ShipmentCurrentEvents createNewState(ShipmentEvents event) {
		// TODO Auto-generated method stub
		ShipmentCurrentEvents newCurrentEvent = new ShipmentCurrentEvents();
		
		newCurrentEvent.setShipment_id(event.getShipmentId());
		newCurrentEvent.setCurrent_status(event.getStatus().toString());
		newCurrentEvent.setStatus_occurred_at(event.getOccurredAt());
		newCurrentEvent.setLocation(event.getLocation());
		newCurrentEvent.setLast_event_id(event.getEventId());
		newCurrentEvent.setUpdated_at(event.getReceivedAt());
		
		return newCurrentEvent;
	}
	
	private ShipmentCurrentEvents getEventById(String eventId) {
		try {

			ShipmentCurrentEvents result = jdbcTemplate.queryForObject("select * from shipment_current_state where last_event_id= ?", new Object[] {
				eventId
		        },
		        new BeanPropertyRowMapper <ShipmentCurrentEvents> (ShipmentCurrentEvents.class));
			return result;
		} catch (EmptyResultDataAccessException e) {
		    return new ShipmentCurrentEvents(); // Or return a new MyObject() if you prefer a "Null Object"
		}
	}
	
	public List<ShipmentCurrentEvents> getEventHistory(String shipmentId) {
		List<ShipmentCurrentEvents> result = jdbcTemplate.query("select * from shipment_current_state where shipment_id= ?", new Object[] {
    			shipmentId
		        },
		        new BeanPropertyRowMapper <ShipmentCurrentEvents> (ShipmentCurrentEvents.class));
		return result;

	}
}
