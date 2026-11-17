package com.karthik.Employee.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwUtil {
	private String SECRET_STRING = "your-very-secure-256-bit-secret-key-must-be-long-enough";

	private final SecretKey key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

	public String generateToken(String username,String sessionId){

		return Jwts.builder()
				.setSubject(username)
				.claim("sessionId", sessionId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
				// In 0.9.x, the order is (Algorithm, Secret)
				.signWith(SignatureAlgorithm.HS256, SECRET_STRING)
				.compact();
	}
	public Claims extractAllClaims(String token){
		return Jwts.parser()             // Use .parser() instead of .parserBuilder()
				.setSigningKey(SECRET_STRING)   // Pass the secret string directly
				.parseClaimsJws(token)
				.getBody();
	}
}
