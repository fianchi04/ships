package com.shipping.ships.service;

import com.shipping.ships.service.domain.Ship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    public List<Ship> getAllShips() {
        List<Ship> ships = new ArrayList<Ship>();
//        Ship ship = new Ship("1","1999", "foo", 10.00, 8.00, 80, "Barry", "400");

        Ship ship = Ship.builder()
                .withId("1")
                .withBuilt("1999")
                .withName("foo")
                .withLengthMeters(10.00)
                .witBeamMeters(8.00)
                .withMaxTEU(80)
                .withOwner("Barry")
                .withGrossTonnage("400")
                .build();
        ships.add(ship);
        return ships;
    }
}
