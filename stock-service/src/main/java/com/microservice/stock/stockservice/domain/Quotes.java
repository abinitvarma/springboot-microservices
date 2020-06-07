package com.microservice.stock.stockservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Quotes {

    private Integer id;

    private String userName;

    private String quote;
}
