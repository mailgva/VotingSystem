package com.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "daily_menu", uniqueConstraints = {@UniqueConstraint(columnNames = { "date", "rest_id", "dish_id"}, name = "dailymenu_unique_date_rest_dish_idx")})
public class DailyMenu extends AbstractBaseEntity{
    @Column(name = "date")
    @NotNull
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Resto resto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;


    public DailyMenu(DailyMenu dm) {
        this(dm.getId(), dm.getDate(), dm.getResto(), dm.getDish());
    }

    public DailyMenu(@NotNull Date date, @NotNull Resto resto, @NotNull Dish dish) {
        this.date = date;
        this.resto = resto;
        this.dish = dish;
    }

    public DailyMenu(Integer id, @NotNull Date date, @NotNull Resto resto, @NotNull Dish dish) {
        super(id);
        this.date = date;
        this.resto = resto;
        this.dish = dish;
    }

    public DailyMenu() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Resto getResto() {
        return resto;
    }

    public void setResto(Resto resto) {
        this.resto = resto;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
