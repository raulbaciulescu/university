package com.example.marire.domain.validator;

import java.util.Objects;

public class PasswordValidator {

    public void validate(String password, String password2) throws Exception {
        if (!Objects.equals(password, password2))
            throw new Exception("parola");
    }
}
