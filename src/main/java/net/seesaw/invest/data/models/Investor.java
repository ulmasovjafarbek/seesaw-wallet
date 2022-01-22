package net.seesaw.invest.data.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;

    private String lastname;

    private String email;

    @ManyToMany(targetEntity = Company.class)
    private Set<Company> savedCompanies = new HashSet<>();
}
