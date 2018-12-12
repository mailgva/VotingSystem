package com.voting.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Resto.GET_BY_NAME, query = "SELECT r FROM Resto r WHERE UPPER(r.name) = UPPER(:name)"),
        @NamedQuery(name = Resto.GET_ALL, query = "SELECT r FROM Resto r ORDER BY r.name")
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "id", name = "restaurants_unique_name_idx")})
public class Resto extends AbstractNamedEntity {
    public static final String GET_BY_NAME = "Resto.getByName";
    public static final String GET_ALL = "Resto.getAll";

    @Column(name = "address")
    private String address;

    @OneToMany
    private List<Dish> dishes;

    public Resto() {
    }

    public Resto(Resto r) {
        this(r.getId(), r.getName(), r.getAddress());
    }

    public Resto(Integer id, String name) {
        super(id, name);
    }

    public Resto(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Resto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dishes=\n'" + getDishes() + '\'' +
                '}';
    }
}
