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
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jwt.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
	

    private final String jwtSecret = "segredo";
    private final String jwtIssuer = "Blog";
    private final String ROLES_KEY = "roles";
    

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getUserId(), user.getUserName()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }
    
    public String createToken(String username, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLES_KEY, roles.stream()
        								.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList()));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
        	System.out.println("Invalid JWT signature - {}"+ ex.getMessage());
        } catch (MalformedJwtException ex) {
        	System.out.println("Invalid JWT token - {}"+ ex.getMessage());
        } catch (ExpiredJwtException ex) {
        	System.out.println("Expired JWT token - {}"+ ex.getMessage());
        } catch (UnsupportedJwtException ex) {
        	System.out.println("Unsupported JWT token - {}"+ ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty - {}"+ ex.getMessage());
        }
        return false;
    }

}
