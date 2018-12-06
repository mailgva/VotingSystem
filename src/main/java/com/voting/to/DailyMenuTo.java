package com.voting.to;

import com.voting.model.Resto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DailyMenuTo{
    private Resto resto;
    private boolean selected;

    public DailyMenuTo(Resto resto, boolean selected) {
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
                "selected=" + selected +
                ", resto=" + resto +
                '}';
    }
}
