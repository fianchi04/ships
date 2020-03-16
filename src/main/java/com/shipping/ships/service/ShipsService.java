package com.shipping.ships.service;

import com.shipping.ships.repository.ShipsRepository;
import com.shipping.ships.repository.domain.ShipEntity;
import com.shipping.ships.service.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class handling ShipsController requests for database information
 */
@Service
public class ShipsService {

    private static final Logger LOG = LoggerFactory.getLogger(ShipsService.class);

    private final ShipsRepository shipsRepository; // todo: replace direct dependencies with controller/service/repository interfaces

    /**
     * Constructor
     * @param shipsRepository the repository class connection to the H2 db
     */
    public ShipsService(ShipsRepository shipsRepository) { this.shipsRepository = shipsRepository;}

    /**
     * Calls repository to get all ships in the db
     * @return the list of ships
     */
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

    /**
     * Calls repository method to get a ship by id
     * @param id the unique id of the ship
     * @return ship
     */
    public Ship getShipById(String id) {
        LOG.info("Getting ship by ID: {}", id);
        Ship ship = shipsRepository.getById(id).toShipModel();
        LOG.info("Ship retrieved successfully from DB: {}", ship);
        return ship;
    }

    /**
     * Calls repository method to get all ships associated with an owner
     * @param owner the name of the owner
     * @return the list of ships
     */
    public List<Ship> getAllShipsByOwner(String owner) {
        LOG.info("Getting all ships by owner: {}", owner);
        List<Ship> ships = new ArrayList<>();

        Iterable<ShipEntity> all = shipsRepository.getAllByOwner(owner);

        for (ShipEntity y : all) {
            ships.add(y.toShipModel());
        }

        return ships;
    }

    /**
     * Calls repository method to delete a ship by its id.
     * @param id the unique ID of the ship to be deleted
     */
    public void deleteShip(String id) {
        LOG.info("Deleting ship by ID: {}", id);
        shipsRepository.delete(
                shipsRepository.getById(id)
        );
    }

    // todo: add service test
}
