package academy.devdojo.youtube.auth.model.mapper;

import academy.devdojo.youtube.auth.model.dto.AccountRequest;
import academy.devdojo.youtube.auth.model.dto.AccountResponse;
import academy.devdojo.youtube.core.model.Account;
import academy.devdojo.youtube.core.service.RequestMapper;
import academy.devdojo.youtube.core.service.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AccountMapper implements RequestMapper<Account, AccountRequest>, ResponseMapper<Account, AccountResponse> {

    private final ModelMapper mapper;

    @Override
    public Account toEntity(final AccountRequest accountRequest) {
        return mapper.map(accountRequest, Account.class);
    }

    @Override
    public List<Account> toEntityList(final List<AccountRequest> accountRequests) {
        return accountRequests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public AccountResponse toResponse(final Account account) {
        return mapper.map(account, AccountResponse.class);
    }

    @Override
    public List<AccountResponse> toResponseList(final List<Account> accounts) {
        return accounts.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
