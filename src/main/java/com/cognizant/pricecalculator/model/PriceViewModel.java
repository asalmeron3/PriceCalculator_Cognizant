package com.cognizant.pricecalculator.model;

import java.util.Objects;

public class PriceViewModel {

    private String productid;
    private String description;
    private int quantity;

    private Double pricePerUnit;
    private Double taxPercent;
    private Double totalTax;
    private Double total;

    public PriceViewModel() {
    }

    public PriceViewModel(String productid, String description, int quantity, Double pricePerUnit, Double taxPercent, Double totalTax, Double total) {
        this.productid = productid;
        this.description = description;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.taxPercent = taxPercent;
        this.totalTax = totalTax;
        this.total = total;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceViewModel that = (PriceViewModel) o;
        return Objects.equals(productid, that.productid) &&
                Objects.equals(description, that.description) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(pricePerUnit, that.pricePerUnit) &&
                Objects.equals(taxPercent, that.taxPercent) &&
                Objects.equals(totalTax, that.totalTax) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productid, description, quantity, pricePerUnit, taxPercent, totalTax, total);
    }
}
