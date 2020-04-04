package academy.devdojo.youtube.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
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
