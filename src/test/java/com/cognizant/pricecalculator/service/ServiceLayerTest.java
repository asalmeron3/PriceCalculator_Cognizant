package com.cognizant.pricecalculator.service;

import com.cognizant.pricecalculator.model.PriceViewModel;
import com.cognizant.pricecalculator.model.Product;
import com.cognizant.pricecalculator.model.Tax;
import com.cognizant.pricecalculator.util.feign.ProductClient;
import com.cognizant.pricecalculator.util.feign.TaxClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @Mock
    ProductClient productClient;

    @Mock
    TaxClient taxClient;

    ServiceLayer service;

    @Before
    public void setUp() {
        service = new ServiceLayer(productClient, taxClient);
    }

    @Test
    public void testCalculateTax() {
        Double totalTaxes = service.calculateTax(100, 6.00);
        Assert.assertEquals(Double.valueOf(6), totalTaxes);
    }

    @Test
    public void testCalculateSubtotal() {
        Double subTotal = service.calculateSubTotal(12.50, 8);
        Assert.assertEquals(Double.valueOf(100), subTotal);
    }

    @Test
    public void testCalculateTotal() {
        Double total = service.calculateTotal(12.50, 8.00);
        Assert.assertEquals(Double.valueOf(20.50), total);
    }

    @Test
    public void testCreateProductViewModel() {
        Product product = new Product("1", "Prodcut test", Double.valueOf(12.50), "Test");
        Tax tax = new Tax("Test", Double.valueOf(6.25), false);

        Mockito.when(taxClient.getTaxByCategory("Test")).thenReturn(tax);
        Mockito.when(productClient.getProductById(1)).thenReturn(product);


        PriceViewModel pvmExpected = new PriceViewModel(
                "1",
                "Prodcut test",
                8,
                Double.valueOf(12.50),
                Double.valueOf(6.25),
                Double.valueOf(6.25),
                Double.valueOf(106.25)
        );

        PriceViewModel actualPvm = service.createPriceViewModel(1, 8, false);

        Assert.assertEquals(pvmExpected, actualPvm);
    }
}
