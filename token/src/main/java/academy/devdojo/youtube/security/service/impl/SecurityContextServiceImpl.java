package academy.devdojo.youtube.security.service.impl;

import academy.devdojo.youtube.core.model.Account;
import academy.devdojo.youtube.security.service.SecurityContextService;
import academy.devdojo.youtube.security.utils.*;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    @Override
    public Account getAuthenticationAccount() {
        return SecurityContextUtil.getAuthenticationAccount();
    }

    @Override
    public Long getAuthenticationAccountId() {
        return getAuthenticationAccount().getId();
    }

}
