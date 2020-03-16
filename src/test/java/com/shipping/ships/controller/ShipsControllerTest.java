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
        String id = "1";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Ship> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ships/" + id, HttpMethod.GET, httpEntity, Ship.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertEquals(id, exchange.getBody().getId());
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
        ResponseEntity<Ship> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ships/164", HttpMethod.GET, httpEntity, Ship.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertEquals(testShip.getId(), exchange.getBody().getId());
        Assertions.assertEquals(testShip.getBuilt(), exchange.getBody().getBuilt());
        Assertions.assertEquals(testShip.getName(), exchange.getBody().getName());
        Assertions.assertEquals(testShip.getLengthMeters(), exchange.getBody().getLengthMeters());
        Assertions.assertEquals(testShip.getBeamMeters(), exchange.getBody().getBeamMeters());
        Assertions.assertEquals(testShip.getMaxTEU(), exchange.getBody().getMaxTEU());
        Assertions.assertEquals(testShip.getOwner(), exchange.getBody().getOwner());
        Assertions.assertEquals(testShip.getGrossTonnage(), exchange.getBody().getGrossTonnage());
    }

    @Test
    public void getAllShipsByIdShouldReturnAListOfShipsAndStatusOK() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        // call the ShipController endpoint for GET all ships
        ResponseEntity<Object> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ships/", HttpMethod.GET, httpEntity, Object.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertNotNull(exchange.getBody());
        // todo: improve assertions on list body
    }

    @Test
    public void getAllShipsByOwnerShouldReturnAListOfShipsAndStatusOK() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        String owner = "CSCL (China)"; // a known owner

        // call the ShipController endpoint for GET all ships by owner
        ResponseEntity<Object> exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ships/?owner=" + owner, HttpMethod.GET, httpEntity, Object.class );

        Assertions.assertEquals(HttpStatus.OK, exchange.getStatusCode());
        Assertions.assertNotNull(exchange.getBody());
        // todo: improve assertions on list body
    }

    @Test
    public void deleteShip() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(headers);

        // call the ShipController endpoint to delete ship with id 1
        ResponseEntity exchange = this.restTemplate.exchange("http://localhost:" + this.port + "/ships/1", HttpMethod.DELETE, httpEntity, Object.class);

        // Assert we receive 204 - No Content, to say request has been actioned immediately (without returning a response body)
        Assertions.assertEquals(HttpStatus.NO_CONTENT, exchange.getStatusCode());

        // Then try to retrieve that same ship again via get and assert that it's not there
        ResponseEntity exchangeCheck = this.restTemplate.exchange("http://localhost:" + this.port + "/ship/1", HttpMethod.GET, httpEntity, Ship.class);

        Assertions.assertNotEquals(HttpStatus.OK, exchangeCheck.getStatusCode());
    }
}
