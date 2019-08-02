package com.cognizant.pricecalculator.controller;

import com.cognizant.pricecalculator.model.PriceViewModel;
import com.cognizant.pricecalculator.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void testGetRoute() throws Exception {
        PriceViewModel pvm = new PriceViewModel();
        pvm.setDescription("This is a test product. Tax exempt false");

        PriceViewModel pvmExemptTax = new PriceViewModel();
        pvmExemptTax.setDescription("This is a test product. Tax exempt true");

        String pvmJson = om.writeValueAsString(pvm);
        String pvmExemptTaxJson = om.writeValueAsString(pvmExemptTax);


        Mockito.when(service.createPriceViewModel(1, 2, false)).thenReturn(pvm);
        Mockito.when(service.createPriceViewModel(1, 2, true)).thenReturn(pvmExemptTax);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2&exemptTax=false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/price/product/1?quantity=2&exemptTax=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(pvmExemptTaxJson));


    }
}
