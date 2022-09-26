package com.diego.supermercado.web.security;

import io.jsonwebtoken.Claims;
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

    public boolean validateToken(String token, UserDetails userDetails){
        if(isTokenExpired(token)){
            logger.info("el token ha expirado");
            return false;
        }

        if(!extractUsernameFromToken(token).equals(userDetails.getUsername())){
            logger.info("El token no corresponde al usuario que esta haciendo el request");
            return false;
        }

        logger.info("token OK");
        return true;
    }

    //obtiene todos los Claims del token
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    /**
     *
     * @param token
     * @return true == expired : the expirationDate is before to current time -> vencido
     * @return false == not expired : the expirationDate is after the current time -> esta vigente
     */
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public String extractUsernameFromToken(String token){
        return this.getClaims(token).getSubject();
    }
}
