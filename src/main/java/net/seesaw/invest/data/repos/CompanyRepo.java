package net.seesaw.invest.data.repos;

import net.seesaw.invest.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CompanyRepo extends JpaRepository<Company, Long> {
}
