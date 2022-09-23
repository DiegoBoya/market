package com.diego.supermercado.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class JWTUtil {
    private static final Logger logger = Logger.getLogger(JWTUtil.class.getName());

    private static final String KEY = " {noop}MARKET";

    public String generateToken(UserDetails userDetails) {
        String token = "empty token";
        try {
            logger.info("generando el token...");
            token = Jwts.builder().setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact();
        } catch (Exception e) {
            logger.info("Error al generar el token");
        }
        return token;
    }
}
