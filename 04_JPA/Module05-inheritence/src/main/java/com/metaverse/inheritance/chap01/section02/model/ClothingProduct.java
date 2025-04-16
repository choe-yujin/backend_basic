package com.metaverse.inheritance.chap01.section02.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLOTHING")
public class ClothingProduct extends Product {
    private String size;
    private String material;
    private String color;

    public ClothingProduct() {}

    public ClothingProduct(Long id, String name, int price, String brand, int stockQuantity, 
                          String size, String material, String color) {
        super(id, name, price, brand, stockQuantity);
        this.size = size;
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return "ClothingProduct{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                "} " + super.toString();
    }
}
