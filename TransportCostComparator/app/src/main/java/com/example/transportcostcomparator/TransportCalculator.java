package com.example.transportcostcomparator;

public class TransportCalculator {

    public double calculateDailyCost(Transport transport) {
        return transport.getDistancePerDay() * transport.getCostPerKm();
    }

    public double calculateMonthlyCost(Transport transport) {
        double dailyCost = calculateDailyCost(transport);
        return dailyCost * transport.getTravelDays();
    }

    public double calculateMonthlyDistance(Transport transport) {
        return transport.getDistancePerDay() * transport.getTravelDays();
    }
}