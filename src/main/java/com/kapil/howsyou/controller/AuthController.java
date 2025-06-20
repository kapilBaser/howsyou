package com.kapil.howsyou.controller;

import com.kapil.howsyou.dto.LoginDto;
import com.kapil.howsyou.dto.Profile;
import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.exception.InvalidUsernameException;
import com.kapil.howsyou.exception.UserAlreadyExist;
import com.kapil.howsyou.service.UserService;
import com.kapil.howsyou.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<Profile> registerUser(@RequestBody @Valid HowsyouUser howsyouUser){
            HowsyouUser savedUser = userService.save(howsyouUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(Profile.mapToProfile(savedUser));
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody @Valid LoginDto loginDto){
        try{

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return jwtUtils.generateToken(loginDto);

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid email/username or password");
        }catch (AuthenticationException e){
            throw new AuthenticationException("Authentication failed") { };
        }
    }

}
