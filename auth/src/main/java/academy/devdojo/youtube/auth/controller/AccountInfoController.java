package academy.devdojo.youtube.auth.controller;

import academy.devdojo.youtube.auth.service.AccountService;
import academy.devdojo.youtube.core.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/user")
public class AccountInfoController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody @Valid final Account account) {
        return new ResponseEntity(accountService.save(account), HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public ResponseEntity<Account> getUserInfo(Principal principal) {
        Account account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(account);
    }

}
