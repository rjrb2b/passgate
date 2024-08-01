package rjr.studio.passgate.conf.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import rjr.studio.passgate.dao.entity.UserEntity;

@Component
public class SecurityJwtToken {

	private final Integer expirationTime = 5 * 60 * 1000; // m * s * 1000
	private final String jwtSecret = "b5840bef8a0e3ed1a923f820a8611aa27ca196a8468dd9a9d499459148931d47";

	public String jwtTokenGenerate(UserEntity userEntity) {

		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

		return Jwts.builder().setSubject(userEntity.getUsername()).claim("roles", userEntity.getRoles())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expirationTime))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

	public boolean jwtTokenValidate(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(
					Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("Invalid JWT Token: " + e.getMessage());
			return false;

		}
	}
}
