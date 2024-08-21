package rjr.studio.passgate.conf.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;

@Component
public class JwtTokenProvider {

	private final Integer EXPIRATION_TIME = 1000 * 60 * 60; // 1000 * 60 seconds * minutes
	private final String SECRET_KEY = "b5840bef8a0e3ed1a923f820a8611aa27ca196a8468dd9a9d499459148931d47";

	// Metodo per generare il token JWT
	public String generateToken(UserEntity userEntity) {
		Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		//@formatter:off
		return Jwts.builder()
				.setSubject(userEntity.getUsername())
				.claim("roles", this.rolesName(userEntity.getRoles()))
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		//@formatter:on
	}

	// Metodo per la validazione del token JWT
	public void validateToken(String token) {
		try {
			Claims claims = this.getAllClaimsFromToken(token);
			if (claims.getExpiration().before(new Date())) {
				throw new RuntimeException("Token is expired");
			}
		} catch (Exception e) {
			// TODO: eccezione da gestire
			System.out.println("***************** ERRORE DA MODIFICARE *****************");
			System.out.println("Invalid JWT Token: " + e.getMessage());
		}
	}

	// Metodo per estrarre le informazioni dell'utente dal token
	public String getUsernameFromToken(String token) {
		Claims claims = this.getAllClaimsFromToken(token);
		return claims.getSubject();
	}

	// (Opzionale) Metodo per estrarre i ruoli dell'utente dal token
	public List<String> getRolesFromToken(String token) {
		Claims claims = this.getAllClaimsFromToken(token);
		return (List<String>) claims.get("roles");
	}

	// (Opzionale) Metodo per verificare se il token è scaduto
	public boolean isTokenExpired(String token) {
		Claims claims = this.getAllClaimsFromToken(token);
		return claims.getExpiration().before(new Date());
	}

	// Integra questo filtro nella configurazione di Spring Security (vedi classe
	// SecurityConfig)
	public OncePerRequestFilter jwtAuthenticationFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				// ... implementa la logica di intercettazione e verifica del token ...
			}
		};
	}

	private Claims getAllClaimsFromToken(String token) {
		//@formatter:off
		return Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();
		//@formatter:on
	}

	private Set<String> rolesName(Set<TypeRoleEntity> roles) {
		return roles.stream().map(TypeRoleEntity::getName).collect(Collectors.toSet());
	}

}
