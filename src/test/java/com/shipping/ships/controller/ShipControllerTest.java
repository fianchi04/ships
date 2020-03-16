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
public class ShipControllerTest {

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
}
