package dev.emanoel.shopcore.security;
import dev.emanoel.shopcore.user.User;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.time.Instant; import java.util.Date;
@Service
public class JwtService {
 private final SecretKey key; private final long expirationSeconds;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-seconds:3600}") long expirationSeconds){this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expirationSeconds=expirationSeconds;}
 public String generate(User u){Instant now=Instant.now(); return Jwts.builder().subject(u.getEmail()).claim("role",u.getRole().name()).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(expirationSeconds))).signWith(key).compact();}
 public String subject(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
}
