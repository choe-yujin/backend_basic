package oop.mission.beverage;

import oop.mission.beverage.coffee.Americano;
import oop.mission.beverage.coffee.Cafelatte;
import oop.mission.beverage.coffee.Cappuccino;
import oop.mission.beverage.coffee.Espresso;

public class Menu {
    private String name = "메뉴판";

    public void printMenu() {
        Americano americano = new Americano();
        Cafelatte cafelatte = new Cafelatte();
        Cappuccino cappuccino = new Cappuccino();
        Espresso espresso = new Espresso();

        System.out.println("------" + name + "------");
        System.out.println(americano.printMenu());
        System.out.println(cafelatte.printMenu());
        System.out.println(espresso.printMenu());
        System.out.println(cappuccino.printMenu());
        System.out.println("-----------------");
    }
}
