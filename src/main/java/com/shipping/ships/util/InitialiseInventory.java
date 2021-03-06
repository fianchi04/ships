package com.shipping.ships.util;

import com.google.gson.Gson;
import com.shipping.ships.controller.ShipsController;
import com.shipping.ships.repository.ShipsRepository;
import com.shipping.ships.repository.domain.ShipEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean called at application start up to load ships.json into H2 DB
 */
public class InitialiseInventory {

    private static final Logger LOG = LoggerFactory.getLogger(ShipsController.class);

    @Autowired
    private ShipsRepository shipsRepository;

    public void init() {
        LOG.info("Initialisation method called by application start up");

        Gson gson = new Gson();
        JSONParser parser = new JSONParser();

        try {
            // read the file contents into an object
            Object obj = parser.parse(new FileReader("src\\main\\resources\\ships.json"));

            JSONObject jsonObject = (JSONObject) obj;

            // Split the file contents into JSON objects each containing one item of the list 'ships'
            JSONArray shipList = (JSONArray) jsonObject.get("ships");

//          initialise an array to hold all the ships
            List<ShipEntity> ships = new ArrayList<>();

            // convert each raw list item object into aN instance of class Ship in the ships list
            for (Object o : shipList) {
                ships.add(
                        gson.fromJson(
                                o.toString(), ShipEntity.class));
            }
            LOG.info("Parsed JSON object into Ships array");
            shipsRepository.saveAll(ships);
            LOG.info("ships.json initialised in H2 memory");

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}


