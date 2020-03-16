package com.shipping.ships;

import com.google.gson.Gson;
import com.shipping.ships.service.domain.Ship;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class  ShipsApplication {


	public static List<Ship> SHIPS;

	public static void main(String[] args) {

		SHIPS = initialiseShipInventory();
		SpringApplication.run(ShipsApplication.class, args);
	}

	public static List<Ship> initialiseShipInventory() {
		Gson gson;
		gson = new Gson();
		JSONParser parser = new JSONParser();

		try {
			// read the file contents into an object
			Object obj = parser.parse(new FileReader("C:\\Users\\avwil\\OneDrive\\Documents\\Code\\ships\\src\\main\\resources\\ships.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// Split the file contents into JSON objects each containing one item of the list 'ships'
			JSONArray shipList = (JSONArray) jsonObject.get("ships");

			// initialise an array to hold all the ships
			List<Ship> ships = new ArrayList<>();

			// print inventory contents
			Iterator<JSONObject> iterator = shipList.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());

			}

			// convert each raw list item object into aN instance of class Ship in the ships list
			for (Object o : shipList) {
				ships.add(
						gson.fromJson(
								o.toString(), Ship.class));
			}

			System.out.println("Ships stored in mem:");
			System.out.println(ships.toString());


			return ships;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
