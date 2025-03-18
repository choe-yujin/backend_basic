package oop.mission.beverage.coffee;

import oop.mission.beverage.Beverage;

public class Cafelatte extends Beverage {
    private String name = "카페라떼";
    private int price = 4000;
    private int menuNum = 2;

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
