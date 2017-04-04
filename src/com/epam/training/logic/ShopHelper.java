package com.epam.training.logic;

import com.epam.training.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class ShopHelper {
	private static final int MAX_RENTED_UNITS_BY_USER = 3;
	private Shop shop;
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	public static Shop loadShopFromFile(File file) throws FileNotFoundException, IOException {
		BufferedReader reader = null;
		Shop shop = new Shop();
		shop.setRentUnits(new ArrayList<RentUnit>());
		Map<SportEquipment, Integer> goods = new HashMap<>();
		shop.setGoods(goods);
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(":");
				int equipmentId = Integer.parseInt(data[0]);
				Category category = Category.valueOf(data[1]);
				String title = data[2];
				int price = Integer.parseInt(data[3]);
				SportEquipment sportEquipment = new SportEquipment(
						equipmentId, 
						category, 
						title, 
						price);
				int quantity = Integer.parseInt(data[4]);
				goods.put(sportEquipment, quantity);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return shop;
	}
	
	public Map<SportEquipment, Integer> getEquipmentByCategory(Category category) {
		if (category == null) {
			return new HashMap<>(0);
		}
		Map<SportEquipment, Integer> result = new HashMap<>();
		Map<SportEquipment, Integer> goods = shop.getGoods();
		for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (category == entry.getKey().getCategory()) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
	
	public Map<SportEquipment, Integer> getEquipmentByTitle(String title) {
		if (title == null) {
			return new HashMap<>(0);
		}
		Map<SportEquipment, Integer> result = new HashMap<>();
		Map<SportEquipment, Integer> goods = shop.getGoods();
		for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			if (title.equals(entry.getKey().getTitle())) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
	
	public void printAvailableGoods() {
		System.out.println("Available goods:");
		printGoods(shop.getGoods());
	}
	
	public void printGoods(Map<SportEquipment, Integer> goods) {
		if (goods == null || goods.isEmpty()) {
			System.out.println("No goods");
			return;
		}
		
		for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {
			SportEquipment equipment = entry.getKey();
			printEquipmentInfo(equipment);
			System.out.println("quantity: " + entry.getValue());
			System.out.println("__________________________");
		}
	}
	
	public void printEquipmentInfo(SportEquipment equipment) {
		System.out.println("id: " + equipment.getEquipmentId());
		System.out.println("title: " + equipment.getTitle());
		System.out.println("category: " + equipment.getCategory());
		System.out.println("price: " + equipment.getPrice());
	}
	
	public void printRentedGoods() {
		System.out.println("Rented goods:");
		printGoods(getRentedGoods());
	}

	private Map<SportEquipment, Integer> getRentedGoods() {
		Map<SportEquipment, Integer> rentedGoods = new HashMap<>();
		for (RentUnit rentUnit : shop.getRentUnits()) {
			for (Entry<SportEquipment, Integer> entry : rentUnit.getUnits().entrySet()) {
				SportEquipment equipment = entry.getKey();
				if (rentedGoods.containsKey(equipment)) {
					rentedGoods.put(equipment, rentedGoods.get(equipment) + entry.getValue());
				} else {
				    rentedGoods.put(equipment, entry.getValue());
				}
			}
		}
		return rentedGoods;
	}
	
	public void rentEquipment(SportEquipment sportEquipment, User user) {
		if (getRentedUnitsCountByUser(user) < MAX_RENTED_UNITS_BY_USER) {
			Map<SportEquipment, Integer> goods = shop.getGoods();
			Integer quantity = goods.get(sportEquipment);
			if (quantity > 0) {
				goods.put(sportEquipment, quantity - 1);
				RentUnit userRentUnit = getRentUnitByUser(user);
				if (userRentUnit == null) {
					userRentUnit = new RentUnit();
					userRentUnit.setUser(user);
					Map<SportEquipment, Integer> units = new HashMap<>();
					units.put(sportEquipment, 1);
					userRentUnit.setUnits(units);
					shop.getRentUnits().add(userRentUnit);
				} else {
					Map<SportEquipment, Integer> units = userRentUnit.getUnits();
					if (units.containsKey(sportEquipment)) {
						units.put(sportEquipment, units.get(sportEquipment) + 1);
					} else {
						units.put(sportEquipment, 1);
					}
				}
			} else {
				System.out.println(sportEquipment.getTitle() + " is not available");
			}
		} else {
			System.out.println("user is not able to rent more than 3 units");
		}
	}

	private RentUnit getRentUnitByUser(User user) {
		for (RentUnit unit : shop.getRentUnits()) {
			if (unit.getUser().equals(user)) {
				return  unit;
			}
		}
		return null;
	}
	
	private int getRentedUnitsCountByUser(User user) {
		RentUnit userRentUnit = getRentUnitByUser(user);
		if (userRentUnit == null) {
			return 0;
		}
		int rentedUnits = 0;
		for (Integer value : userRentUnit.getUnits().values()) {
			rentedUnits += value;
		}
		return rentedUnits;
	}
}
