package com.cognizant.pricecalculator.service;

import com.cognizant.pricecalculator.model.PriceViewModel;
import com.cognizant.pricecalculator.model.Product;
import com.cognizant.pricecalculator.model.Tax;
import com.cognizant.pricecalculator.util.feign.ProductClient;
import com.cognizant.pricecalculator.util.feign.TaxClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private TaxClient taxClient;

    public ServiceLayer(ProductClient productClient, TaxClient taxClient) {
        this.productClient = productClient;
        this.taxClient = taxClient;
    }

    public PriceViewModel createPriceViewModel(int productId, int quantity, Boolean taxExempt) {

//        Product product = productClient.getProductById(productId);
//
//        Double subtotal = calculateSubTotal(product.getPricePerUnit(), quantity);
//
//        double taxRate = 0;
//
//        if(taxExempt == false) {
//            String category = product.getCategory();
//            Tax tax = taxClient.getTaxByCategory(category);
//            taxRate = tax.getTaxPercent();
//        }
//
//        Double totalTaxed = calculateTax(subtotal,  taxRate);


        return null;

    }

    public Double calculateTax(double price, double tax) {
        Double totalTax = Double.valueOf(price*(tax/100));
        return totalTax;
    }

    public Double calculateSubTotal(double unitPrice, int quantity) {
        Double subtotal = Double.valueOf(unitPrice*quantity);
        return subtotal;
    }

}
