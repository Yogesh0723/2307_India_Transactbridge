package com.example.transactbridge.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String secretKey = "your_secret_key"; // Use a strong secret key

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // Add any claims you want to include in the token
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        com.auth0.jwt.JWTCreator.Builder jwtBuilder = JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)); // 10 hours expiration

        // Add claims individually
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            if (entry.getValue() instanceof String) {
                jwtBuilder.withClaim(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Integer) {
                jwtBuilder.withClaim(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Boolean) {
                jwtBuilder.withClaim(entry.getKey(), (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof Date) {
                jwtBuilder.withClaim(entry.getKey(), (Date) entry.getValue());
            }
        }

        return jwtBuilder.sign(algorithm);
    }

    public Boolean validateToken(String token, String username) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        String extractedUsername = decodedJWT.getSubject();
        return (extractedUsername.equals(username) && !isTokenExpired(decodedJWT));
    }

    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        return decodedJWT.getSubject();
    }

    private Boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }
}