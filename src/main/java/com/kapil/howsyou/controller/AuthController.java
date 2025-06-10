package com.kapil.howsyou.controller;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.service.UserService;
import com.kapil.howsyou.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public HowsyouUser registerUser(@RequestBody HowsyouUser howsyouUser){
        if(howsyouUser != null && howsyouUser.getEmail() != null){
            return userService.save(howsyouUser);
        }
        return null;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody HowsyouUser howsyouUser){
        if(howsyouUser != null && howsyouUser.getEmail() != null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(howsyouUser.getEmail(), howsyouUser.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return jwtUtils.generateToken(howsyouUser);
        }
        return "";
    }

}
