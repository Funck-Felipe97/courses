package academy.devdojo.youtube.auth.service;

import academy.devdojo.youtube.core.model.Account;
import academy.devdojo.youtube.core.repository.AccountRepository;
import academy.devdojo.youtube.core.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AccountService implements GenericService<Account, Long> {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account save(Account account) {
        account.setRole("USER");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Class<Account> getClazz() {
        return Account.class;
    }
}
