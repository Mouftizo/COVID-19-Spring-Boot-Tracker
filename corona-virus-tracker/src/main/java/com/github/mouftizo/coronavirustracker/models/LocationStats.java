package com.github.mouftizo.coronavirustracker.models;

public class LocationStats {
    String state;
    String country;
    Integer latestTotal;
    String description;
    Integer diffFromPreviousDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLatestTotal() {
        return latestTotal;
    }

    public void setLatestTotal(Integer latestTotal) {
        this.latestTotal = latestTotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiffFromPreviousDay() {
        return diffFromPreviousDay;
    }

    public void setDiffFromPreviousDay(Integer diffFromPreviousDay) {
        this.diffFromPreviousDay = diffFromPreviousDay;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotal=" + latestTotal +
                ", description='" + description + '\'' +
                '}';
    }
}
