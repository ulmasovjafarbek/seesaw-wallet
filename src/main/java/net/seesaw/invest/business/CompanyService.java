package net.seesaw.invest.business;

import net.seesaw.invest.web.CompanyResponse;

import java.util.List;
import java.util.Set;

public interface CompanyService {

    List<CompanyResponse> getMatchingCompanies(int sustainabilityIndex, int profitabilityIndex);
}
