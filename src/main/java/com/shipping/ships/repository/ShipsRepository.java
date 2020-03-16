package com.shipping.ships.repository;

import com.shipping.ships.repository.domain.ShipEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipsRepository extends CrudRepository<ShipEntity, Integer> {

    ShipEntity getById(String id);

    Iterable<ShipEntity> getAllByOwner(String owner);

}
