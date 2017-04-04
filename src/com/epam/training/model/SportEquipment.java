package com.epam.training.model;

public class SportEquipment {
	private int equipmentId;
	private Category category;
	private String title;
	private int price;
	
	public SportEquipment(int equipmentId, Category category, String title, int price) {
		super();
		this.equipmentId = equipmentId;
		this.category = category;
		this.title = title;
		this.price = price;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return equipmentId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SportEquipment other = (SportEquipment) obj;
		if (equipmentId != other.equipmentId)
		return false;
		return true;
	}
}
