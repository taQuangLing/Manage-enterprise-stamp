package com.icheck.backend.util;

import org.springframework.stereotype.Component;

@Component
public class Utilities {
    public String genCode(Long userId, Integer i){
        int numZero = 10 - userId.toString().length() - i.toString().length();
        StringBuilder code = new StringBuilder(userId.toString());
        for (int j = 0; j < numZero; j++) {
            code.append('0');
        }
        code.append(i.toString());
        return code.toString();
    }
}
