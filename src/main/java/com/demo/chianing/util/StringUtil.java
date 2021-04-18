package com.demo.chianing.util;

import java.util.Optional;

public class StringUtil {

    private static final String EMPTY_STRING = "";

    public static boolean isEmpty(String str) {
        return str == null || EMPTY_STRING.equals(str);
    }

    public static boolean isBlank(String str) {
        return isEmpty(Optional.ofNullable(str).map(String::trim).orElse(EMPTY_STRING));
    }

}
