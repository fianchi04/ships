package com.shipping.ships.controller;

import com.shipping.ships.service.domain.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShipsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getShipByIdShouldReturnStatusOKAndShipAsJson() {
        Ship testShip =  Ship.builder()
                .withId("1")
                .withBuilt("1999")
                .withName("foo")
                .withLengthMeters(10.00)
                .witBeamMeters(8.00)
                .withMaxTEU(80)
                .withOwner("Barry")
                .withGrossTonnage("400")
                .build();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Ship> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ship/1", HttpMethod.GET, httpEntity, Ship.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertEquals(testShip.getId(), exchange.getBody().getId());
    }

    @Test
    public void getShipByIdShouldReturnStatusOKAndThatShipAsJson() {
        // Create, for test ref data, an instance of Ship that we know is on the json file
        Ship testShip =  Ship.builder()
                .withId("164")
                .withBuilt("2011")
                .withName("CMA CGM Alaska")
                .withLengthMeters(366)
                .witBeamMeters(48)
                .withMaxTEU(13092)
                .withOwner("CMA CGM (France)")
                .withGrossTonnage("140259")
                .build();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        // call the ShipController endpoint for GET 1 Ship by ID
        ResponseEntity<Ship> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ship/164", HttpMethod.GET, httpEntity, Ship.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertEquals(testShip.getId(), exchange.getBody().getId());
    }
}
