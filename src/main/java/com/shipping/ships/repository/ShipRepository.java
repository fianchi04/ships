package com.shipping.ships.repository;

import com.shipping.ships.repository.domain.ShipEntity;
import com.shipping.ships.service.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends CrudRepository<ShipEntity, Integer> {

    ShipEntity findById(String id);

    Iterable<ShipEntity> getAllByOwner(String owner);

}
