package com.shipping.ships.service;

import com.shipping.ships.repository.ShipsRepository;
import com.shipping.ships.repository.domain.ShipEntity;
import com.shipping.ships.service.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipsService {

    private static final Logger LOG = LoggerFactory.getLogger(ShipsService.class);

    private final ShipsRepository shipsRepository;

    public ShipsService(ShipsRepository shipsRepository) { this.shipsRepository = shipsRepository;}

    public List<Ship> getAllShips() {
        LOG.info("Getting all ships from db");
        List<Ship> ships = new ArrayList<>();

        Iterable<ShipEntity> all = shipsRepository.findAll();

        for (ShipEntity y : all) {
            ships.add(y.toShipModel());
        }

        LOG.info("All ships successfully retrieved from DB");
        return ships;
    }

    public Ship getShipById(String id) {
        LOG.info("Getting ship by ID: {}", id);
        Ship ship = shipsRepository.getById(id).toShipModel();
        LOG.info("Ship retrieved successfully from DB: {}", ship);
        return ship;
    }

    public List<Ship> getAllShipsByOwner(String owner) {
        LOG.info("Getting all ships by owner: {}", owner);
        List<Ship> ships = new ArrayList<>();

        Iterable<ShipEntity> all = shipsRepository.getAllByOwner(owner);

        for (ShipEntity y : all) {
            ships.add(y.toShipModel());
        }

        return ships;
    }

    // todo: error handling
    public void deleteShip(String id) {
        LOG.info("Deleting ship by ID: {}", id);
        shipsRepository.delete(
                shipsRepository.getById(id)
        );
    }
}
