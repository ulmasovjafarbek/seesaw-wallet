package net.seesaw.invest.data.repos;

import net.seesaw.invest.data.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface InvestorRepo extends JpaRepository<Investor, Long> {
}
