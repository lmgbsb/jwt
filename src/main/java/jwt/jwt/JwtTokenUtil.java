package jwt.jwt;

import static java.lang.String.format;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jwt.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
	

    private final String jwtSecret = "segredo";
    private final String jwtIssuer = "Blog";
    private final String ROLES_KEY = "roles";
    

   
    public String createToken(User user, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims()
        		.setSubject(format("%s,%s", user.getUserId(), user.getUserName()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // 1 week
        claims.put(ROLES_KEY, roles.stream()
        								.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList()));
        return Jwts.builder() 
                .setClaims(claims)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }
}
