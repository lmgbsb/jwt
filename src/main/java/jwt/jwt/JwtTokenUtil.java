package jwt.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jwt.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
	

    private final String jwtIssuer = "Blog";
    private final String ROLES_KEY = "roles";
    private final String jwtSecret = "umSegredoQueNinguemNoMundoConseguiraDescobrir";
    private SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

   
    public String createToken(User user, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims()
        		.setSubject(user.getUserName())
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // 1 week
        claims.put(ROLES_KEY, roles.stream()
        								.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList()));
        return Jwts.builder() 
                .setClaims(claims)
                .signWith(key)
                .compact();
    }
    public boolean isValidToken(String token) {
        try {
        	Jws<Claims> jws = Jwts.parserBuilder()
        						.setSigningKey(key) 
        						.build() 
        						.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        	System.out.println(e);
            return false;
        }
    }
    public String getUsername(String token) {
    	Jws<Claims> jws = Jwts.parserBuilder()
				.setSigningKey(key) 
				.build() 
				.parseClaimsJws(token);    	
    	return jws.getBody().getSubject();
    }
    public List<GrantedAuthority> getRoles(String token) {
    	Jws<Claims> jws = Jwts.parserBuilder()
				.setSigningKey(key) 
				.build() 
				.parseClaimsJws(token);
    	List<Map<String, String>>  roleClaims = jws.getBody().get(ROLES_KEY, List.class);    	
        return roleClaims.stream().map(roleClaim ->
                new SimpleGrantedAuthority(roleClaim.get("authority")))
                .collect(Collectors.toList());
    }
}
