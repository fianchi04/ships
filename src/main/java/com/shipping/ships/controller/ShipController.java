package com.shipping.ships.controller;

import com.google.gson.Gson;
import com.shipping.ships.repository.ShipRepository;
import com.shipping.ships.service.ShipService;
import com.shipping.ships.service.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ship")
public class ShipController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipController.class);
    private final ShipService shipService;

    /**
     * Constructor
     * @param shipService Handles retrieving data from the repository and creating a format which the controller can parse.
     */
    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    /**
     *  one to get all the ships of a specific owner -
     */

    @GetMapping(path = "/", produces =  {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ship>> getAllShips() {
        LOGGER.info("Connection made to getAllShips endpoint in controller");
        //todo: return all ships

        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(shipService.getAllShips(),
                headers, HttpStatus.OK);
    }

    /**
     * Get ship by ID.
     * @param id the unique identifier of the ship.
     * @return the ship as json.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ship> getShipbyId(
            @PathVariable (name = "id") int id
    ) {
        LOGGER.info("Received request to get ship by id: {}", id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(shipService.getShipById(id), headers, HttpStatus.OK);
    }

    /**
     * Get ship by owner.
     * @param owner the name of the owner of the ship.
     * @return the ship as json.
     */
    @RequestMapping(value = "/{owner}", method = RequestMethod.GET)
    public ResponseEntity<List<Ship>> getShipbyOwner(
            @PathVariable(value = "owner") String owner
    ) {
        LOGGER.info("Received request to get ship by owner: {}", owner);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(shipService.getShipsByOwner(owner), headers, HttpStatus.OK);
    }

    /**
     * deletes ships.
     */
    @GetMapping(path = "/{id}/delete")
    public ResponseEntity deleteShip(
            @PathVariable(value = "id") int id
    ) {
        LOGGER.info("Received request to delete ship, id: {}", id);
        shipService.deleteShip(id);
        ResponseEntity responseEntity = new ResponseEntity(OK);
        LOGGER.info("Successfully deleted ship");
        return responseEntity;
    }

}