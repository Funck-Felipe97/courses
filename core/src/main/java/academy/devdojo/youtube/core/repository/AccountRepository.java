package academy.devdojo.youtube.core.repository;

import academy.devdojo.youtube.core.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

}
