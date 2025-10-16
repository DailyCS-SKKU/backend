package cse.skku.edu.dailycs.service.auth;

import cse.skku.edu.dailycs.util.enumType.UserStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${JWT_SECRET}")
    private String base64Secret;
    @Value("${JWT_EXPIRATION_TTL}")
    private Duration accessTtl;
    @Value("${JWT_REFRESH_EXPIRATION_TTL}")
    private Duration refreshTtl;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] bytes = Decoders.BASE64.decode(base64Secret);
        this.secretKey = io.jsonwebtoken.security.Keys.hmacShaKeyFor(bytes);
    }


    public String generateAccessToken(Long userId, String email, UserStatus role) {
        return buildToken(userId, accessTtl, Map.of("email", email, "role", role));
    }

    public String generateRefreshToken(Long userId) {
        return buildToken(userId, refreshTtl, Map.of("typ", "refresh"));
    }

    private String buildToken(Long userId, Duration ttl, Map<String, Object> claims) {
        Instant now = Instant.now();
        Instant exp = now.plus(ttl);

        return Jwts.builder()
                .header().type("JWT").and()           // header.typ = JWT
                .subject(String.valueOf(userId))         // sub
                .issuedAt(Date.from(now))                // iat
                .expiration(Date.from(exp))              // exp
                .claims(claims)                          // custom claims
                .signWith(secretKey, Jwts.SIG.HS256)     // HS256
                .compact();
    }

    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        String t = stripBearer(token);
        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)         // HS256 검증 키
                .clockSkewSeconds(60)       // 시계 오차 허용(옵션)
                .build();

        Jws<Claims> jws = parser.parseSignedClaims(t);
        return jws.getPayload();
    }

    public Long getUserId(String token) {
        return Long.valueOf(parseClaims(token).getSubject());
    }

    public String getRole(String token) {
        Object role = parseClaims(token).get("role");
        return role != null ? role.toString() : null;
    }

    private String stripBearer(String token) {
        if (token == null) return "";
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }

    public String parseJWT(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("No authentication token found.");
        }
    }
}
