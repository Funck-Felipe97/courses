package academy.devdojo.youtube.security.service;

import academy.devdojo.youtube.core.model.Account;

public interface SecurityContextService {

    Account getAuthenticationAccount();

    Long getAuthenticationAccountId();

}
