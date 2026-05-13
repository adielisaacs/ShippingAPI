package com.shipping.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.api.modal.ShipmentEvents;

import java.util.List;
import java.util.Optional;

public interface ShipmentEventRepository extends JpaRepository<ShipmentEvents, Long> {


}