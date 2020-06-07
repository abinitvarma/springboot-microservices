package com.microservice.stock.stockservice.resources;

import com.microservice.stock.stockservice.domain.Quotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock/rest")
public class StockResource {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${db-service.url}")
    private String dbServiceUrl;

    @GetMapping("/{userName}")
    public List<Stock> getQuotes(@PathVariable("userName") String userName){
            ResponseEntity<List<Quotes>> responseEntity = restTemplate.exchange(dbServiceUrl + userName, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Quotes>>() { });

            List<Stock> stocks = getStocksFromYahoo(responseEntity.getBody());
            return stocks;
    }

    private List<Stock> getStocksFromYahoo(List<Quotes> quotes) {
        List<Stock> stocks = new ArrayList<>();
        for(Quotes quote : quotes){
            try {
                stocks.add(YahooFinance.get(quote.getQuote()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stocks;
    }


}
