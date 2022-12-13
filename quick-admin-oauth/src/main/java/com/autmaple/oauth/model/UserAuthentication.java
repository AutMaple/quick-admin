package com.autmaple.oauth.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAuthentication {
    private String username;
    private String password;
    private List<String> roles;
}
