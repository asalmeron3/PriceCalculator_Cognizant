package com.cognizant.pricecalculator.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import feign.FeignException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<VndErrors> invalidBoolean(IllegalArgumentException e, WebRequest request){
        VndErrors error= new VndErrors(request.toString(),e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<VndErrors> invalidIntegerFormat(NumberFormatException e, WebRequest request){
        String msg =  "An integer was expected in the request. Please correct and try again.";
        VndErrors error= new VndErrors(request.toString(), msg);
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(value = {FeignException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<VndErrors> feignClientResourceNotFound(FeignException e, WebRequest request){
        String msg = "The resource(s) could not be found. No matching product or tax category found.";
        VndErrors error= new VndErrors(request.toString(), msg);
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}