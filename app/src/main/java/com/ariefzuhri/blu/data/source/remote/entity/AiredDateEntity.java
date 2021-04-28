package com.ariefzuhri.blu.data.source.remote.entity;

public class AiredDateEntity {

    private final String startDate;
    private final String endDate;

    public AiredDateEntity(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AiredDateEntity(String date) {
        this.startDate = date;
        this.endDate = date;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
