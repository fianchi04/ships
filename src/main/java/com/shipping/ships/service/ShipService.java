package com.shipping.ships.service;

import com.shipping.ships.repository.ShipRepository;
import com.shipping.ships.service.domain.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService{

    private final  ShipRepository shipRepository;

    public ShipService( ShipRepository shipRepository) { this.shipRepository = shipRepository;}

    public List<Ship> getAllShips() {
        List<Ship> ships = new ArrayList<>();

        Iterable<Ship> all = shipRepository.findAll();

        all.forEach(ships::add);

        return ships;
    }

    public Ship getShipById(int id) {
        return shipRepository.findById(id);
    }

    public List<Ship> getShipsByOwner(String owner) {
        List<Ship> ships = new ArrayList<>();

        Iterable<Ship> all = shipRepository.getAllByOwner(owner);

        all.forEach(ships::add);

        return ships;
    }

    public void deleteShip(int id) {
        shipRepository.delete(
                shipRepository.findById(id)
        );
    }
}
