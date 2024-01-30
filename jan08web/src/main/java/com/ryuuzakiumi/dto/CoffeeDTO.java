package com.ryuuzakiumi.dto;

public class CoffeeDTO {
	String hotOrCold, beverage, teaChoice;
	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getHotOrCold() {
		return hotOrCold;
	}

	public void setHotOrCold(String hotOrCold) {
		this.hotOrCold = hotOrCold;
	}

	public String getBeverage() {
		return beverage;
	}

	public void setBeverage(String beverage) {
		this.beverage = beverage;
	}

	public String getTeaChoice() {
		return teaChoice;
	}

	public void setTeaChoice(String teaChoice) {
		this.teaChoice = teaChoice;
	}

}
