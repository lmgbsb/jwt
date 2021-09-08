package jwt.service;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jwt.jwt.JwtTokenUtil;
import jwt.model.User;
import jwt.repository.UserRepository;
import jwt.security.UserPrincipal;

@Service
public class BlogUserDetailsService implements UserDetailsService {

	
	private final UserRepository userRepository;
	private final JwtTokenUtil jwtTokenUtil;
	
	
	public BlogUserDetailsService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
		this.userRepository = userRepository;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Cannot find username: " + username);
		}
		//Decorator design pattern
		return new UserPrincipal(user);
	}
	public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
        if (jwtTokenUtil.isValidToken(jwtToken)) {
            return Optional.of(
                withUsername(jwtTokenUtil.getUsername(jwtToken))
                .authorities(jwtTokenUtil.getRoles(jwtToken))
                .password("") //token does not have password but field may not be empty
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build());
        }
        return Optional.empty();
    }
}
