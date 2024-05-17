package com.gogoasa.c.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class Helper {
    public static String getUsernameFromBearerToken(String token) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();


        return userDetails.getUsername();
    }
}
