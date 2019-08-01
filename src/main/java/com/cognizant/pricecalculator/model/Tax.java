package com.cognizant.pricecalculator.model;

public class Tax {

    private String category;
    private Double taxPercent;
    private Boolean taxExempt;

    public Tax() {
    }

    public Tax(String category, Double taxPercent, Boolean taxExempt) {
        this.category = category;
        this.taxPercent = taxPercent;
        this.taxExempt = taxExempt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Boolean getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }
}