package com.icheck.backend.util;

import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.security.AdminAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private String SECRET_KEY = "linh1234";
    private final int MUNINUS = 1;
    // Tạo một JWT token dựa trên userDetails
    public String generateToken(AdminAccount adminAccount){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", adminAccount.getId());
        return createToken(claims, adminAccount.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) // set ngày phát hành
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * MUNINUS)) // set thời gian sống là 10 giờ
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    public Long getClaimByKey(String token, String key) {
        final Claims claims = extractAllClaim(token);
        return claims.get("id", Long.class);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Lấy token và trả về người dùng.
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    // Kiểm tra thời gian sống của token
    public Boolean isTokenExpired(String token){
        Date date =  extractExpiration(token);
        if (date == null)throw new ApiException(ErrorMessage.TOKEN_ERROR);
        return date.before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // check tên người dùng trong userDetails có trùng với tên trong token hay
    public Boolean validateToken(String token, @NotNull AdminAccount adminAccount){
        final String username = extractUsername(token);
        return (username.equals(adminAccount.getUsername()) && !isTokenExpired(token));
    }
}
