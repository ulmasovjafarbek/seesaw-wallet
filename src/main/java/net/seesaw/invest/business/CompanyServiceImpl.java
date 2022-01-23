package net.seesaw.invest.business;

import net.seesaw.invest.data.repos.CompanyRepo;
import net.seesaw.invest.web.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<CompanyResponse> getMatchingCompanies(int desiredS, int desiredP) {
        List<CompanyResponse> companies = companyRepo.findAll().stream().map(
                company -> {
                    Double indexS = Double.valueOf(desiredS - company.getSustainabilityIndex());
                    Double indexP = Double.valueOf(desiredP - company.getProfitabilityIndex());
                    Double absDistance = Math.sqrt(Math.pow(indexS, 2) + Math.pow(indexP, 2));

                    return new CompanyResponse(company.getId(), company.getName(), company.getSymbol(),
                            company.getSustainabilityIndex(), company.getProfitabilityIndex(), absDistance);
                }
        ).collect(Collectors.toList());

        return getSortedCompanies(companies);
    }

    private List<CompanyResponse> getSortedCompanies(List<CompanyResponse> companies) {
        List<CompanyResponse> indexedCompanies = getIndexedCompanies(companies);
        Collections.sort(indexedCompanies, new Comparator<CompanyResponse>() {
            @Override
            public int compare(CompanyResponse c1, CompanyResponse c2) {
                return (int) (c1.getSeesawIndex() - c2.getSeesawIndex());
            }
        });
        Collections.reverse(indexedCompanies);
        return indexedCompanies;
    }

    private List<CompanyResponse> getIndexedCompanies(List<CompanyResponse> companies) {
        Double minDistance = getMinDistance(companies);
        Double maxDistance = getMaxDistance(companies);

        return companies.stream().map(
                company -> {
                    Double similarityIndex = 100 - ((company.getAbsDistance() - minDistance) /
                            (maxDistance - minDistance)) * 100;
                    company.setSeesawIndex(similarityIndex);
                    return company;
                }
        ).collect(Collectors.toList());
    }

    private Double getMinDistance(List<CompanyResponse> companies) {
        Double min = 100.0;
        for (int i = 0; i < companies.size(); i++) {
            Double distance = companies.get(i).getAbsDistance();
            min = distance < min ? distance : min;
        }
        return min;
    }

    private Double getMaxDistance(List<CompanyResponse> companies) {
        Double max = 0.0;
        for (int i = 0; i < companies.size(); i++) {
            Double distance = companies.get(i).getAbsDistance();
            max = distance > max ? distance : max;
        }
        return max;
    }

}
