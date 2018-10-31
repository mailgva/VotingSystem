package com.voting.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "dishes_unique_name_idx")})
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 2500)
    private double price;

    public Dish(@Range(min = 10, max = 2500) double price) {
        this.price = price;
    }

    public Dish(Integer id, String name, @Range(min = 10, max = 2500) double price) {
        super(id, name);
        this.price = price;
    }

    public Dish() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
