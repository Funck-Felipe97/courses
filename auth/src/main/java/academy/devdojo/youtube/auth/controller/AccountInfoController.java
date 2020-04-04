package academy.devdojo.youtube.auth.controller;

import academy.devdojo.youtube.core.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class AccountInfoController {

    @GetMapping("/info")
    public ResponseEntity<Account> getUserInfo(Principal principal) {
        Account account = (Account) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ResponseEntity.ok(account);
    }

}
