package academy.devdojo.youtube.core.repository;

import academy.devdojo.youtube.core.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account findByUsername(String username);

}
