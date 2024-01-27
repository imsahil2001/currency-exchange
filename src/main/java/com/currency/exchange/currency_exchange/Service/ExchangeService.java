package com.currency.exchange.currency_exchange.Service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExchangeService {

    Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    RestTemplate restTemplate;

    public Map<String, Object> manipulateRate(Double actualRate){

        Double calculatedRate = null;
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Map<String, Object> response = restTemplate
                    .getForObject("https://api.currencyfreaks.com/v2.0/rates/latest?apikey=4c2ee46e55574c1f972af59e7b054616",
                                    Map.class);

            Map<String, String> ratesMap = (Map<String, String>) response.get("rates");
            logger.info("RatesMap :: --- {}", ratesMap);
            Double chosenCurrencyRateFromBase = Double.valueOf(ratesMap.get("INR"));
            logger.info("chosenCurrencyRateFromBase :: --- {}", chosenCurrencyRateFromBase);
            calculatedRate = chosenCurrencyRateFromBase * actualRate;

            responseMap.put("Message", "Success");
            responseMap.put("Status", HttpStatus.OK.getReasonPhrase());
            responseMap.put("Calculated Rate", calculatedRate);

        }catch(Exception e ){
            responseMap.put("Message", "Failed");
            responseMap.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseMap;
    }
}
