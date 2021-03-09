package com.epam.jdi.bookstore.security;

import com.epam.jdi.bookstore.utils.gson.GsonIgnoreStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.epam.jdi.bookstore.BookstoreApiApplication.logger;

@Component
public class JwtTokenProvider {

    @Value("12345")
    private String jwtSecret;

    @Value("28800000")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) throws ParseException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();

//        SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");
//        String strdate = "01-01-2022";
//        Date expiryDate = dateformat.parse(strdate);

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Gson gson = new GsonBuilder().setExclusionStrategies(new GsonIgnoreStrategy()).create();
        return Jwts.builder()
                .setSubject(gson.toJson(userPrincipal))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        UserPrincipal userPrincipal = new Gson().fromJson(claims.getSubject(), UserPrincipal.class);
        return userPrincipal.id;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
