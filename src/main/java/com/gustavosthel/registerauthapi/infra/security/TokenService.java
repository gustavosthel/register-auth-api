package com.gustavosthel.registerauthapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gustavosthel.registerauthapi.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.OffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final OffsetScrollPositionHandlerMethodArgumentResolver offsetResolver;
    @Value("${api.security.token.secret}")
    private String secret;

    public TokenService(OffsetScrollPositionHandlerMethodArgumentResolver offsetResolver) {
        this.offsetResolver = offsetResolver;
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpireDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating Token");
        }
    }



    private Instant generateExpireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
