package com.kapil.howsyou.utils;

import com.kapil.howsyou.dto.LoginDto;
import com.kapil.howsyou.entity.HowsyouUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String KEY;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(KEY.getBytes());
    }


    public String generateToken(LoginDto loginDto){
        return Jwts.builder()
                .setSubject(loginDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ((1000 * 60) * 20)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String authToken) {
        return getClaims(authToken)
                .getSubject();

    }

    private Claims getClaims(String authToken) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userDetails, String authToken) {
        return username.equals(userDetails.getUsername()) && !isTokenExpired(authToken);
    }

    private boolean isTokenExpired(String authToken) {
        return getClaims(authToken).getExpiration().before(new Date());
    }
}
