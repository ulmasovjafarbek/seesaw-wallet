package net.seesaw.invest.web;

import lombok.Data;

@Data
public class CompanyResponse {

    private long id;
    private String name;
    private String symbol;
    private Double sustainabilityIndex;
    private Double profitabilityIndex;
    private Double absDistance;
    private Double seesawIndex;

    public CompanyResponse(long id, String name, String symbol, Double sustainabilityIndex, Double profitabilityIndex, Double absDistance) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.sustainabilityIndex = sustainabilityIndex;
        this.profitabilityIndex = profitabilityIndex;
        this.absDistance = absDistance;
    }
}
