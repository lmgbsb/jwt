package jwt.jwt;

import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.io.IOException;
import jwt.service.BlogUserDetailsService;

public class JwtTokenFilter extends GenericFilterBean {

	
    private static final String BEARER = "Bearer";
    private BlogUserDetailsService userDetailsService;

    
    public JwtTokenFilter(BlogUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        //Check for Authorization:Bearer JWT
        String headerValue = ((HttpServletRequest)req).getHeader("Authorization");
        getBearerToken(headerValue).ifPresent(token-> {
            //Pull the Username and Roles from the JWT to construct the user details
            userDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
                //Add the user details (Permissions) to the Context for just this API invocation
                SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
            });
        });

        try {
	        //move on to the next filter in the chains
	        filterChain.doFilter(req, res);
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }
        return Optional.empty();
    }
}