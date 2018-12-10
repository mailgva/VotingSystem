package com.voting.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.voting.model.Resto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class DailyMenuTo{
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private Resto resto;
    private boolean selected;

    public DailyMenuTo(Date date, Resto resto, boolean selected) {
        this.date = date;
        this.resto = resto;
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


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "date=" + date +
                ", selected=" + selected +
                ", resto=" + resto +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
