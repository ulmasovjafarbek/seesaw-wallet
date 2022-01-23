package net.seesaw.invest.web;

import net.seesaw.invest.business.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calculate")
@CrossOrigin("*")
public class CompanyIndexRest {

    @Autowired
    private CompanyServiceImpl companyService;

    public CompanyIndexRest(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping(consumes = "application/json")
    List<CompanyResponse> getIndexedCompanies(@RequestBody CalculateRequest request) {
        try {
            return companyService.getMatchingCompanies(request.getDesiredS(), request.getDesiredP());
        } catch (Exception e) {
            System.out.println("Exception occured");
            return null;
        }
    }
}
