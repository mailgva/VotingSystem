package com.voting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "id", name = "restaurants_unique_name_idx")})
public class Resto extends AbstractNamedEntity {
    @Column(name = "address")
    private String address;

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

    @Override
    public String toString() {
        return "Resto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
