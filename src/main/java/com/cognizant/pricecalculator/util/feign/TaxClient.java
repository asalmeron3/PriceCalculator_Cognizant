package com.cognizant.pricecalculator.util.feign;

import com.cognizant.pricecalculator.model.Product;
import com.cognizant.pricecalculator.model.Tax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tax-repository")
public interface TaxClient {

    @RequestMapping(value = "/taxes/{category}", method = RequestMethod.GET)
    public Tax getTaxByCategory(@PathVariable String category);
}
