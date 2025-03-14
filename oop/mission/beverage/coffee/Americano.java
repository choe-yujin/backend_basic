package oop.mission.beverage.coffee;

import oop.mission.beverage.Beverage;

public class Americano extends Beverage {
    private String name = "아메리카노";
    private int price = 3000;
    private int menuNum = 1;

    public String printMenu() {
        return this.menuNum + ". " + this.name + ": " + this.price + "원";
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
