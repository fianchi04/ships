package com.shipping.ships.controller;

import com.shipping.ships.service.ShipsService;
import com.shipping.ships.service.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ships")
public class ShipsController {
    private static final Logger LOG = LoggerFactory.getLogger(ShipsController.class);
    private final ShipsService shipsService;

    //todo: mdc

    /**
     * Constructor
     * @param shipsService Handles retrieving data from the repository and creating a format which the controller can parse.
     */
    @Autowired
    public ShipsController(ShipsService shipsService) {
        this.shipsService = shipsService;
    }


    /**
     * Root URL, returns all the ships
     * @return List<Ship> a JSON list of all the ships as response body
     */
    @GetMapping(path = "/", produces =  {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ship>> getAllShips() {
        LOG.info("Connection made to getAllShips endpoint in controller");
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity responseEntity = new ResponseEntity(shipsService.getAllShips(), headers, HttpStatus.OK);
        LOG.info("Successfully created response with all ships as response body");
        return responseEntity;

    }

    /**
     * Get all ships by owner.
     * @param owner the name of the owner of the ship (Query parameter).
     * @return the ships as json list.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Ship>> getAllShipsByOwner(
            @RequestParam(value = "owner") String owner
    ) {
        LOG.info("Received request to get all ships by owner: {}", owner);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity responseEntity = new ResponseEntity(shipsService.getAllShipsByOwner(owner), headers, HttpStatus.OK);
        LOG.info("Successfully created response with all ships by owner name");
        return responseEntity;
    }

    /**
     * Get ship by ID.
     * @param id the unique identifier of the ship.
     * @return the ship as json.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ship> getShipbyId(
            @PathVariable (name = "id") String id
    ) {
        LOG.info("Received request to get ship by id: {}", id);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity responseEntity = new ResponseEntity(shipsService.getShipById(id), headers, HttpStatus.OK);
        LOG.info("Successfully created response with ship found by id");
        return responseEntity;
    }

    /**
     * Delete a ship by ID
     * @param id the unique ID of the ship
     * @return 200 OK if successful
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteShip(
            @PathVariable(value = "id") String id
    ) {
        LOG.info("Received request to delete ship, id: {}", id);
        shipsService.deleteShip(id);
        ResponseEntity responseEntity = new ResponseEntity(NO_CONTENT);
        LOG.info("Successfully deleted ship");
        return responseEntity;
    }

}
