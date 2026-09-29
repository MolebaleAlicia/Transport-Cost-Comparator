package com.example.transportcostcomparator;

public class Recommendation {

    public String getCostCategory(double monthlyCost) {
        String category;
        if (monthlyCost < 1000) {
            category = "Low Cost";
        } else if (monthlyCost < 2000) {
            category = "Moderate";
        } else if (monthlyCost < 3000) {
            category = "High";
        } else if (monthlyCost < 5000) {
            category = "Very High";
        } else {
            category = "Excessive";
        }
        return category;
    }

    public String getRecommendation(String category) {
        String recommendation;
        switch (category) {
            case "Low Cost":
                recommendation = "Continue using your current transport method.";
                break;
            case "Moderate":
                recommendation = "Consider carpooling where possible.";
                break;
            case "High":
                recommendation = "Reduce unnecessary trips and combine errands.";
                break;
            case "Very High":
                recommendation = "Consider public transport for regular commuting.";
                break;
            case "Excessive":
                recommendation = "Immediate action is recommended to reduce transport costs.";
                break;
            default:
                recommendation = "No recommendation available.";
                break;
        }
        return recommendation;
    }
}