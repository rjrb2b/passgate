package rjr.studio.passgate.conf.security;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class AIJwtTokenProvider {
	
	@Value("${app.jwt.key.private}")
	private String jwtPrivateKey;

	@Value("${app.jwt.key.public}")
	private String jwtPublicKey;

	@Value("${app.jwt.expirationInMs}")
	private Long jwtExpirationInMs;

	@Value("${app.jwt.expirationTimeDeltaInMs}")
	private Long jwtExpirationTimeDeltaInMs;

	private final String secretKey = "SegretoPerLaFirmaDelToken";

	public String createToken(String username) throws Exception {
		PrivateKey privateKey = null;
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		try {
			privateKey = this.convertToPrivateKey(jwtPrivateKey);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		//formatter:off
		return Jwts.builder().
				setSubject(username)
				.claim("role", "ADMIN")
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(privateKey, SignatureAlgorithm.RS256)
				.compact();
		//formatter:on
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	// Metodo privato per convertire la stringa della chiave privata in un oggetto
	// PrivateKey
	private PrivateKey convertToPrivateKey(String privateKeyString) throws Exception {
		PrivateKey rtn = null;
		KeyFactory keyFactory = null;

		try {
			byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			keyFactory = KeyFactory.getInstance("RSA");
			rtn = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new InvalidKeySpecException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		}

		return rtn;
	}
	
	// Metodo privato per convertire la stringa della chiave pubblica in un oggetto
	// PublicKey
	private PublicKey convertToPublicKey(String publicKeyString) throws Exception {
		PublicKey rtn = null;
		try {
			byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory;
			keyFactory = KeyFactory.getInstance("RSA");
			rtn = keyFactory.generatePublic(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new InvalidKeySpecException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		}
		return rtn;
	}
}
