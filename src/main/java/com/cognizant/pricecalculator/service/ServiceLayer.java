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

        Product product = productClient.getProductById(productId);

        String category = product.getCategory();
        Tax tax = taxClient.getTaxByCategory(category);

        double taxRate;

        if(tax.getTaxExempt() && taxExempt) {
            taxRate = 0.00;
        } else {
            taxRate = tax.getTaxPercent();
        }

        Double subtotal = calculateSubTotal(product.getPricePerUnit(), quantity);
        Double totalTaxed = calculateTax(subtotal,  taxRate);
        Double total = calculateTotal(subtotal,totalTaxed);

        PriceViewModel pvm = new PriceViewModel(
                Integer.toString(productId),
                product.getProductDescription(),
                quantity,
                product.getPricePerUnit(),
                taxRate,
                totalTaxed,
                total
        );

        return pvm;
    }

    public Double calculateTax(double price, double tax) {
        Double totalTax = Double.valueOf(price*(tax/100));
        return totalTax;
    }

    public Double calculateSubTotal(double unitPrice, int quantity) {
        Double subtotal = Double.valueOf(unitPrice*quantity);
        return subtotal;
    }

    public Double calculateTotal(Double subtotal, Double totalTax) {
        return totalTax + subtotal;
    }
}
