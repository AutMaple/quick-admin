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

    @Test
    public void numberTest() {
        Long a = 10L;
        Double b = 10.2;
        double c = a;
        System.out.println(c);
        c = b;
        System.out.println(c);
    }
}
