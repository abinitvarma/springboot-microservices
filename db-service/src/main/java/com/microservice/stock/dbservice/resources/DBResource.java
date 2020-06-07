package com.microservice.stock.dbservice.resources;


import com.microservice.stock.dbservice.domains.Quote;
import com.microservice.stock.dbservice.entities.Quotes;
import com.microservice.stock.dbservice.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock/db")
public class DBResource {

    @Autowired
    private QuoteRepository quoteRepository;

    @GetMapping("/{userName}")
    public List<Quotes> getQuotesByUserName(@PathVariable("userName") String userName){
            return quoteRepository.findByUserName(userName);
    }

    @PostMapping("/addQuote")
    public List<Quotes> saveQuotesForUser(@RequestBody final Quote quote){
        List<Quotes> quotesEntity = mapQuoteToEntity(quote);

        List<Quotes> savedQuotes = quoteRepository.saveAll(quotesEntity);

        return savedQuotes;
    }

    @PostMapping("/delete/{userName}")
    public String deleteQuotesForUser(@PathVariable("userName") String userName){
        List<Quotes> quotesForDelete = quoteRepository.findByUserName(userName);

        if(quotesForDelete == null || quotesForDelete.isEmpty() ){
            return "No details Found for the User!!!";
        }
        quoteRepository.deleteAll(quotesForDelete);
        return quotesForDelete.size() + " records deleted!!";
    }

    private List<Quotes> mapQuoteToEntity(Quote quote) {
        List<Quotes> quoteList = new ArrayList<Quotes>();
        for(String quoteName : quote.getQuotes()){
            Quotes quotes = new Quotes();
            quotes.setUserName(quote.getUsername());
            quotes.setQuote(quoteName);
            quoteList.add(quotes);
        }
        return quoteList;
    }
}
