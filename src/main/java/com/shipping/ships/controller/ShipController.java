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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ships")
public class ShipController {
    private static final Logger LOG = LoggerFactory.getLogger(ShipController.class);
    private final ShipService shipService;

    //todo: mdc

    /**
     * Constructor
     * @param shipService Handles retrieving data from the repository and creating a format which the controller can parse.
     */
    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }


    /**
     * Root URL, returns all the ships
     * @return
     */
    @GetMapping(path = "/", produces =  {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ship>> getAllShips() {
        LOG.info("Connection made to getAllShips endpoint in controller");

        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(shipService.getAllShips(),
                headers, HttpStatus.OK);
    }

    /**
     * Get ship by owner.
     * @param owner the name of the owner of the ship.
     * @return the ship as json.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Ship>> getAllShipsByOwner(
            @RequestParam(value = "owner") String owner
    ) {
        LOG.info("Received request to get all ships by owner: {}", owner);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(shipService.getShipsByOwner(owner), headers, HttpStatus.OK);
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
        return new ResponseEntity<>(shipService.getShipById(id), headers, HttpStatus.OK);
    }

    /**
     * deletes ships.
     */
    @GetMapping(path = "/{id}/delete")
    public ResponseEntity deleteShip(
            @PathVariable(value = "id") String id
    ) {
        LOG.info("Received request to delete ship, id: {}", id);
        shipService.deleteShip(id);
        ResponseEntity responseEntity = new ResponseEntity(OK);
        LOG.info("Successfully deleted ship");
        return responseEntity;
    }

}
