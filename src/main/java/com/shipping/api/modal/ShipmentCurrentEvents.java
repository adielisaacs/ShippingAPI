package com.shipping.api.modal;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="shipment_current_state")
public class ShipmentCurrentEvents {

	@Id
    private String shipment_id;

	private String current_status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date status_occurred_at;

    private String location;

    private String last_event_id;

    private Long event_count;

    @Lob
    private String state_reason;

   
    private Date updated_at;


    public String getShipment_id() {
		return shipment_id;
	}

	public void setShipment_id(String shipment_id) {
		this.shipment_id = shipment_id;
	}

	public String getCurrent_status() {
		return current_status;
	}


	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}


	public Date getStatus_occurred_at() {
		return status_occurred_at;
	}


	public void setStatus_occurred_at(Date status_occurred_at) {
		this.status_occurred_at = status_occurred_at;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getLast_event_id() {
		return last_event_id;
	}


	public void setLast_event_id(String last_event_id) {
		this.last_event_id = last_event_id;
	}


	public Long getEvent_count() {
		return event_count;
	}


	public void setEvent_count(Long event_count) {
		this.event_count = event_count;
	}


	public String getState_reason() {
		return state_reason;
	}


	public void setState_reason(String state_reason) {
		this.state_reason = state_reason;
	}


	public Date getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
}



