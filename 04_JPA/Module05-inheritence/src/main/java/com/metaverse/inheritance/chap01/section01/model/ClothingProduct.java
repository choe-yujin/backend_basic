package com.metaverse.inheritance.chap01.section01.model;

public class ClothingProduct {
    private String name;
    private double price;
    private String brand;
    private int stockQuantity;
    private String size;
    private String material;

    public ClothingProduct(String name, double price, String brand, int stockQuantity, String size, String material) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.stockQuantity = stockQuantity;
        this.size = size;
        this.material = material;
    }

    @Override
    public String toString() {
        return "ClothingProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", size='" + size + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
