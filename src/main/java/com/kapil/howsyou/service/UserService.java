package com.kapil.howsyou.service;

import com.kapil.howsyou.entity.HowsyouUser;
import com.kapil.howsyou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public HowsyouUser save(HowsyouUser howsyouUser){
        howsyouUser.setPassword(passwordEncoder.encode(howsyouUser.getPassword()));
        return userRepository.save(howsyouUser);
    }

    public HowsyouUser findByEmail(String email){
        return userRepository.findByEmail(email).isPresent() ? userRepository.findByEmail(email).get() : null;
    }

    public HowsyouUser findByUserId(long userId){
        return userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }


}
