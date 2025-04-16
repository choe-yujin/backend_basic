package mission.f_generics.model;

import mission.f_generics.interfaces.Cuttable;

public class Apple implements Cuttable {
    private String name;
    private String color;
    private double price;

    public Apple(String name, String color, double price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Override
    public String cut() {
        if (color.equals("빨간색")) {
            return "익은 " + name + " 껍질을 깎습니다.";
        } else {
            return "덜 익은 " + name + " 껍질을 깎습니다.";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
