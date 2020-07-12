package academy.devdojo.youtube.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void encryptPassword() {
        System.out.println("Password: " + new BCryptPasswordEncoder().encode("felipe"));
    }

}
