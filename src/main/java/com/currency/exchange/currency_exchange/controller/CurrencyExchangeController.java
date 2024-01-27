package com.currency.exchange.currency_exchange.controller;

import com.currency.exchange.currency_exchange.Service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    @Autowired
    ExchangeService exchangeService;

    @PostMapping("/inr")
    public ResponseEntity<?> exchangeToInr(@NonNull @RequestParam(name = "rate") Double rate){
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.manipulateRate(rate));
    }
}
