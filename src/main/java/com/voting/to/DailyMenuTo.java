package com.voting.to;

import com.voting.model.AbstractBaseEntity;
import com.voting.model.DailyMenu;
import com.voting.model.Dish;
import com.voting.model.Resto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class DailyMenuTo{
    private Resto resto;
    private Dish dish;
    private boolean selected;

    public DailyMenuTo(Resto resto, Dish dish, boolean selected) {
        this.resto = resto;
        this.dish = dish;
        this.selected  = selected;
    }

    public DailyMenuTo() {
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "resto=" + resto +
                ", dish=" + dish +
                ", selected=" + selected +
                '}';
    }
}
