package academy.devdojo.youtube.security.utils;

import academy.devdojo.youtube.core.model.Account;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;

@Slf4j
public class SecurityContextUtil {

    private SecurityContextUtil() {

    }

    /**
     * Setting security properties on spring security context
     *
     * @param signedJWT Signed token
     */
    public static void setSecurityContext(SignedJWT signedJWT) {
        try {
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            String username = claims.getSubject();

            if (username == null) {
                throw new JOSEException("Username missing from JWT");
            }

            List<String> authorities = claims.getStringListClaim("authorities");
            Account account = Account
                    .builder()
                    .id(claims.getLongClaim("userId"))
                    .username(username)
                    .role(join(",", authorities))
                    .build();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, null, createAuthorities(authorities));
            auth.setDetails(signedJWT.serialize());

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            log.error("Error setting security context", e);
            SecurityContextHolder.clearContext();
        }
    }

    private static List<SimpleGrantedAuthority> createAuthorities(List<String> authorities) {
        return authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static Account getAuthenticationAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
