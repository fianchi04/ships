package com.shipping.ships.repository;

import com.shipping.ships.service.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShipRepository extends CrudRepository<Ship, Integer> {

    Ship findById(int id);

    Iterable<Ship> getAllByOwner(String owner);

}
