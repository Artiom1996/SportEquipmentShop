package com.epam.training.model;

import java.util.List;
import java.util.Map;

public class Shop {

	private Map<SportEquipment, Integer> goods;

	private List<RentUnit> rentUnits;

	public Map<SportEquipment, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<SportEquipment, Integer> goods) {
		this.goods = goods;
	}

	public List<RentUnit> getRentUnits() {
		return rentUnits;
	}

	public void setRentUnits(List<RentUnit> rentUnits) {
		this.rentUnits = rentUnits;
	}
}
