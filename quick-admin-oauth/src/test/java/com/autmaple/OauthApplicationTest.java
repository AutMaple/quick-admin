package com.autmaple;

import org.jetbrains.annotations.TestOnly;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class OauthApplicationTest {
    @Test
    public void bcryptEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd =  encoder.encode("111111");
        System.out.println(pwd);
    }
}
