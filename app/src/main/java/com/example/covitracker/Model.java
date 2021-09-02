package com.example.covitracker;

public class Model {
    private String state;
    private String totalActive;
    private String totalConfirmed;
    private String totalDeceased;
    private String totalRecovered;

    public Model(String state, String totalActive, String totalConfirmed, String totalDeceased, String totalRecovered) {

        this.state = state;
        this.totalActive = totalActive;
        this.totalConfirmed = totalConfirmed;
        this.totalDeceased = totalDeceased;
        this.totalRecovered = totalRecovered;
    }

    public Model(){}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(String totalActive) {
        this.totalActive = totalActive;
    }

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public String getTotalDeceased() {
        return totalDeceased;
    }

    public void setTotalDeceased(String totalDeceased) {
        this.totalDeceased = totalDeceased;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
}
