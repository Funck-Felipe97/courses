package academy.devdojo.youtube.security.token;

import academy.devdojo.youtube.core.model.ApplicationUser;
import academy.devdojo.youtube.core.property.JwtConfiguration;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenCreator {

    private final JwtConfiguration jwtConfiguration;

    /**
     * Assinando JWT
     *
     * @param authentication
     * @return
     */
    @SneakyThrows
    public SignedJWT createSignedJWT(Authentication authentication) {
        log.info("Starting to create the signed JWT");

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();

        JWTClaimsSet jwtClaimSet = createJWTClaimSet(authentication, applicationUser);

        KeyPair keyPair = generateKeyPair();

        log.info("Building JWK from te RSA Keys");

        JWK jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).keyID(UUID.randomUUID().toString()).build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .jwk(jwk)
                        .type(JOSEObjectType.JWT)
                        .build(),
                jwtClaimSet);

        log.info("Signing the token with the private RSA Key");

        RSASSASigner signer = new RSASSASigner(keyPair.getPrivate());

        signedJWT.sign(signer);

        return signedJWT;
    }

    private JWTClaimsSet createJWTClaimSet(Authentication authentication, ApplicationUser applicationUser) {
        log.info("Creating the JwtClainSet Object for '{}'", applicationUser);

        return new JWTClaimsSet.Builder()
                .subject(applicationUser.getUsername())
                .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim("userId", applicationUser.getId())
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
                .build();
    }

    @SneakyThrows
    private KeyPair generateKeyPair() {
        log.info("Generating RSA 2048 bits Keys");

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        return generator.genKeyPair();
    }

    public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        log.info("Starting the encryptYoken method");

        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(), new Payload(signedJWT));

        log.info("Encrypting token with system's private key");

        jweObject.encrypt(directEncrypter);

        log.info("Token encrypted");

        return jweObject.serialize();
    }


}
