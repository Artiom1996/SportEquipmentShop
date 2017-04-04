package com.epam.training.main;

import com.epam.training.logic.ShopHelper;
import com.epam.training.model.Category;
import com.epam.training.model.Shop;
import com.epam.training.model.SportEquipment;
import com.epam.training.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Shop shop = ShopHelper.loadShopFromFile(new File("shop.txt"));
			
			ShopHelper helper = new ShopHelper();
			helper.setShop(shop);
			helper.printAvailableGoods();
			helper.printRentedGoods();
			
			SportEquipment carpet = helper.getEquipmentByCategory(Category.YOGA).keySet().iterator().next();
			SportEquipment ball = helper.getEquipmentByCategory(Category.FOOTBALL).keySet().iterator().next();
			
		
			User user = new User(1, "Vasya");
			helper.rentEquipment(carpet, user);
			helper.rentEquipment(carpet, user);
			helper.rentEquipment(carpet, user);
			helper.printAvailableGoods();
			helper.rentEquipment(ball, user);
			helper.rentEquipment(ball, user);
			helper.rentEquipment(ball, user);
			helper.printAvailableGoods();
			helper.printRentedGoods();
			
			User user2 = new User(2, "Petya");
			helper.rentEquipment(carpet, user2);
			helper.rentEquipment(carpet, user2);
			helper.printAvailableGoods();
			helper.printRentedGoods();
			
			helper.printGoods(helper.getEquipmentByCategory(Category.TENNIS));
			helper.printGoods(helper.getEquipmentByTitle("Football ball"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Input error");
		}
	}
}
