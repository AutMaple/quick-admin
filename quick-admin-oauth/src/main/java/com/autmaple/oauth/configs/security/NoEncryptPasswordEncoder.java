package com.autmaple.oauth.configs.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 开发时，不进行加密
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
