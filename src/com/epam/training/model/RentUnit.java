package com.epam.training.model;

import java.util.Map;

public class RentUnit {
	//maps SportEquipment and quantity of rented equipment by the user
	private Map<SportEquipment, Integer> units;
	
	private User user;
	
	public Map<SportEquipment, Integer> getUnits() {
		return units;
	}
	
	public void setUnits(Map<SportEquipment, Integer> units) {
		this.units = units;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
