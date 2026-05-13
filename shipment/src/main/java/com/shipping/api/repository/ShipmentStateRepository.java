package com.shipping.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shipping.api.modal.ShipmentCurrentEvents;

public interface ShipmentStateRepository extends JpaRepository<ShipmentCurrentEvents, Long> {
	
	
	}
