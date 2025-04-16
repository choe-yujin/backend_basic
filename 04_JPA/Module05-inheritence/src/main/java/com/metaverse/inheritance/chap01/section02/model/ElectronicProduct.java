package com.metaverse.inheritance.chap01.section02.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ELECTRONIC")
public class ElectronicProduct extends Product {
    private int warrantyPeriod;
    private String powerConsumption;

    public ElectronicProduct() {}

    public ElectronicProduct(Long id, String name, int price, String brand, int stockQuantity, int warrantyPeriod, String powerConsumption) {
        super(id, name, price, brand, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
        this.powerConsumption = powerConsumption;
    }

    @Override
    public String toString() {
        return "ElectronicProduct{" +
                "warrantyPeriod=" + warrantyPeriod +
                ", powerConsumption='" + powerConsumption + '\'' +
                "} " + super.toString();
    }
}
