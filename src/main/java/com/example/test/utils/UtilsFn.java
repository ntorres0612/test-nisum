package com.example.test.utils;

import com.example.test.constant.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UtilsFn {

    private static UtilsFn instance;

    private UtilsFn() {
    }

    public static UtilsFn getInstance() {
        if(instance == null) {
            instance = new UtilsFn();
        }

        return instance;
    }
    public boolean validateEmail(String email, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(Constant.passwordPatter);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
