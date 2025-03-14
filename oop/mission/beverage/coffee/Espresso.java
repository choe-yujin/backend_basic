package oop.mission.beverage.coffee;


import oop.mission.beverage.Beverage;

public class Espresso extends Beverage {
    private String name = "에스프레소";
    private int price = 2500;
    private int menuNum = 3;

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
