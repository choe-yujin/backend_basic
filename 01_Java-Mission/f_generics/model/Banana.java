package mission.f_generics.model;

import mission.f_generics.interfaces.Peelable;

public class Banana implements Peelable {
    private String name;
    private int ripeness;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRipeness() {
        return ripeness;
    }

    public void setRipeness(int ripeness) {
        this.ripeness = ripeness;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Banana(String name, int ripeness, double price) {
        this.name = name;
        this.ripeness = ripeness;
        this.price = price;
    }

    @Override
    public String peel() {
        if (ripeness < 4) {
            return "덜 익은 " + name + " 껍질을 조심히 벗깁니다";
        } else if (ripeness > 8) {
            return "많이 익은 " + name + " 껍질을 쉽게 벗깁니다";
        } else {
            return name + " 껍질을 벗깁니다";
        }
    }
}
