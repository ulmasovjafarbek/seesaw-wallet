package net.seesaw.invest.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String symbol;

    private float price;

    private Double marketCap;

    private Double sustainabilityIndex;

    private Double profitabilityIndex;
}
