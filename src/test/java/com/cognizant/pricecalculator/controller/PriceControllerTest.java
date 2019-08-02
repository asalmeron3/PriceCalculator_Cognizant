package com.cognizant.pricecalculator.controller;

import com.cognizant.pricecalculator.model.PriceViewModel;
import com.cognizant.pricecalculator.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @MockBean
    ServiceLayer service;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();
    private String pvmJson;
    private String pvmExemptTaxJson;

    @Before
    public void setUp() throws Exception {
        PriceViewModel pvm = new PriceViewModel();
        pvm.setDescription("This is a test product. Tax exempt false");

        PriceViewModel pvmExemptTax = new PriceViewModel();
        pvmExemptTax.setDescription("This is a test product. Tax exempt true");

        pvmJson = om.writeValueAsString(pvm);
        pvmExemptTaxJson = om.writeValueAsString(pvmExemptTax);

        Mockito.when(service.createPriceViewModel(1, 2, false)).thenReturn(pvm);
        Mockito.when(service.createPriceViewModel(1, 2, true)).thenReturn(pvmExemptTax);
        Mockito.when(service.createPriceViewModel(0, 2, true)).thenThrow(FeignException.class);

    }

    @Test
    public void testGetRouteQueryIncludesBothQuantityAndExemptTaxFalse() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2&exemptTax=false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmJson));
    }

    @Test
    public void testGetRouteWithQuantityPresentButExemptTaxIsNull() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmJson));

    }

    @Test
    public void testGetRouteQueryIncludesBothQuantityAndExemptTaxTrue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2&exemptTax=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmExemptTaxJson));
    }


    @Test
    public void testGetRouteQuantityTypoIncludesStringShouldThrowNumberFormatException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2e&exemptTax=true"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRouteExemptTacBooleanTypoIncludesStringShouldThrowIllegalArgumentException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2&exemptTax=true9"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRouteProductIdNotFoundThrowsFeignException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/0?quantity=2&exemptTax=true"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
