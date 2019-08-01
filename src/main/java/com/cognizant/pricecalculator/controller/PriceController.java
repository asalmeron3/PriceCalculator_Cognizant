package com.cognizant.pricecalculator.controller;

import com.cognizant.pricecalculator.model.PriceViewModel;
import com.cognizant.pricecalculator.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/price/product")
public class PriceController {

    @Autowired
    ServiceLayer service;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public PriceViewModel getPriceInformation(
            @PathVariable int productId,
            @RequestParam(name = "quantity") int quantity,
            @RequestParam(name ="taxExempt", required = false) Boolean taxExempt)
    {
        Boolean removeTax = false;

        if (taxExempt == null) {
            removeTax = false;
        } else if (taxExempt == true) {
            removeTax = true;
        }
        return service.createPriceViewModel(productId, quantity, removeTax);
    }
}
