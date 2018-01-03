package oleg.podolyan.ammodpsu.repository.report;

import oleg.podolyan.ammodpsu.domain.report.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByName(String name);

	List<Account> findAllByDeletedIsFalse();
}
