package com.shipping.ships.service;

import com.shipping.ships.repository.ShipRepository;
import com.shipping.ships.repository.domain.ShipEntity;
import com.shipping.ships.service.domain.Ship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService{

    private final  ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) { this.shipRepository = shipRepository;}

    public List<Ship> getAllShips() {
        List<Ship> ships = new ArrayList<>();

        Iterable<ShipEntity> all = shipRepository.findAll();

        for (ShipEntity y : all) {
            ships.add(y.toShipModel());
        }

        return ships;
    }

    public Ship getShipById(String id) {
        return shipRepository.findById(id).toShipModel();
    }

    public List<Ship> getShipsByOwner(String owner) {
        List<Ship> ships = new ArrayList<>();

        Iterable<ShipEntity> all = shipRepository.getAllByOwner(owner);

        for (ShipEntity y : all) {
            ships.add(y.toShipModel());
        }

        return ships;
    }

    // todo: error handling
    public void deleteShip(String id) {
        shipRepository.delete(
                shipRepository.findById(id)
        );
    }
}
