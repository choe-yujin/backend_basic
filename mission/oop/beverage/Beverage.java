package mission.oop.beverage;

public class Beverage {
    private String name;
    private int price;
    private int menuNum;

    public String printMenu() {
        return menuNum + ". " + name + ": " + price + "원";
    }
}
