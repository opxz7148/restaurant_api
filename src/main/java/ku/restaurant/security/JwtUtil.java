package ku.restaurant.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    String jwtSecret;

    @Value("${jwt.expiration}")
    int jwtExpirationMs;

    private SecretKey key;

    @PostConstruct
    public void init() {
        System.out.println(jwtSecret);
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
    // Get username from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
    // Validate JWT token
    public boolean validateJwtToken(String token) throws JwtException {
        try {
            Jwts.parser()
                    .verifyWith(key).build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }
}
