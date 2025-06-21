package com.kapil.howsyou.utils;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    @Autowired
    private UserService userService;

    public HowsyouUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(username != null){
            if(username.contains("@"))
                return userService.findByEmail(username);
            return userService.findByUsername(username);
        }
        return null;
    }
}
