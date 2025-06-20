package com.kapil.howsyou.service;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.exception.InvalidUsernameException;
import com.kapil.howsyou.exception.UserAlreadyExist;
import com.kapil.howsyou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public HowsyouUser save(HowsyouUser howsyouUser){
        List<String> cause = new ArrayList<>();

        if(isUsernameExists(howsyouUser.getUsername())){
            cause.add("Username already exists");
        }
        if(isEmailExists(howsyouUser.getEmail())){
            cause.add("Email already exists");
        }
        if(!cause.isEmpty()){
            throw new UserAlreadyExist("User Already Exist", cause);
        }

        for(char c: howsyouUser.getUsername().toCharArray()){
            if(!(c == '_' || c == '.' || (int) c >= 'A' && (int) c <= 'Z' || (int) c >= 'a' && (int) c <= 'z' || (int) c >= '0' && (int) c <= '9')){
                cause.add("Username : Username contains invalid characters");
                throw new InvalidUsernameException("Invalid Username", cause);
            }
        }
        howsyouUser.setPassword(passwordEncoder.encode(howsyouUser.getPassword()));
        return userRepository.save(howsyouUser);
    }

    public HowsyouUser findByEmail(String email){
        return userRepository.findByEmail(email).isPresent() ? userRepository.findByEmail(email).get() : null;
    }

    public HowsyouUser findByUserId(long userId){
        return userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;
    }

    public boolean isUsernameExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<HowsyouUser> user;
        if(username.contains("@"))
            user = userRepository.findByEmail(username);
        else
            user = userRepository.findByUsername(username);
        if(user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("Username/Email not found");
    }


}
