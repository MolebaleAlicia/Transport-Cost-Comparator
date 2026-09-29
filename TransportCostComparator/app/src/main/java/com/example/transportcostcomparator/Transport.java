package com.example.transportcostcomparator;

public class Transport {

    private String modeOfTransport;
    private double distancePerDay;
    private double costPerKm;
    private int travelDays;

    public Transport() {
        this.modeOfTransport = "";
        this.distancePerDay = 0.0;
        this.costPerKm = 0.0;
        this.travelDays = 0;
    }

    public Transport(String modeOfTransport, double distancePerDay, double costPerKm, int travelDays) {
        this.modeOfTransport = modeOfTransport;
        this.distancePerDay = distancePerDay;
        this.costPerKm = costPerKm;
        this.travelDays = travelDays;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public double getDistancePerDay() {
        return distancePerDay;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public void setDistancePerDay(double distancePerDay) {
        this.distancePerDay = distancePerDay;
    }

    public void setCostPerKm(double costPerKm) {
        this.costPerKm = costPerKm;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }
}