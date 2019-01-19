package com.voting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = DailyMenu.DELETE, query = "DELETE FROM DailyMenu dm WHERE dm.id=:id"),
        @NamedQuery(name = DailyMenu.DELETE_BY_DATE, query = "DELETE FROM DailyMenu dm WHERE dm.date=:date"),
        @NamedQuery(name = DailyMenu.GET,
                query = "SELECT dm FROM DailyMenu dm " +
                        "JOIN FETCH dm.resto " +
                        "JOIN FETCH dm.dish " +
                        "WHERE dm.id=:id"),
        @NamedQuery(name = DailyMenu.GET_ALL,
                query = "SELECT dm FROM DailyMenu dm " +
                        "JOIN FETCH dm.resto " +
                        "JOIN FETCH dm.dish " +
                        "ORDER BY dm.date DESC, dm.resto.name "),
        @NamedQuery(name = DailyMenu.GET_BY_DATE,
                query = "SELECT dm FROM DailyMenu dm " +
                        "JOIN FETCH dm.resto " +
                        "JOIN FETCH dm.dish " +
                        "WHERE dm.date=:date ORDER BY dm.resto.name"),
        @NamedQuery(name = DailyMenu.GET_BY_NAME_RESTO,
                query = "SELECT dm FROM DailyMenu dm " +
                        "JOIN FETCH dm.resto " +
                        "JOIN FETCH dm.dish " +
                        "WHERE UPPER(dm.resto.name) LIKE CONCAT('%',:name,'%') ORDER BY dm.date DESC, dm.resto.name ")
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = DailyMenu.GENERATE_DAILY_MENU,
                procedureName = "generatedailymenu",
                parameters = {
                        @StoredProcedureParameter(
                                name = "fromdate",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "todate",
                                type = String.class,
                                mode = ParameterMode.IN),
                        }
        ),
        @NamedStoredProcedureQuery(
                name = DailyMenu.GENERATE_DAILY_DISHES,
                procedureName = "generatedailydishes",
                parameters = {
                        @StoredProcedureParameter(
                                name = "fromdate",
                                type = Date.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "todate",
                                type = Date.class,
                                mode = ParameterMode.IN)
                }
        )
})

@Entity
@Table(name = "daily_menu", uniqueConstraints = {@UniqueConstraint(columnNames = { "date", "rest_id", "dish_id"}, name = "dailymenu_unique_date_rest_dish_idx")})
public class DailyMenu extends AbstractBaseEntity{
    public static final String DELETE =             "DailyMenu.delete";
    public static final String DELETE_BY_DATE =     "DailyMenu.deleteByDate";
    public static final String GET =                "DailyMenu.get";
    public static final String GET_ALL =            "DailyMenu.getAll";
    public static final String GET_BY_DATE =        "DailyMenu.getByDate";
    public static final String GET_BY_NAME_RESTO =  "DailyMenu.getByNameResto";
    public static final String GENERATE_DAILY_MENU =  "DailyMenu.generateDailyMenu";
    public static final String GENERATE_DAILY_DISHES =  "DailyMenu.generateDailyDishes";



    @Column(name = "date")
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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

    @Override
    public String toString() {
        return "DailyMenu{" +
                "id=" + id +
                ", date=" + date +
                ", resto=" + resto +
                ", dish=" + dish +
                '}';
    }
}
