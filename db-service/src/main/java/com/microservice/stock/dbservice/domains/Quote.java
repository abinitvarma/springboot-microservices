package com.microservice.stock.dbservice.domains;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter @NoArgsConstructor
public class Quote {

    private Integer id;

    private String username;

    private List<String> quotes;
}
