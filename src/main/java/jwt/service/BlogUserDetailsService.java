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
	private final JwtTokenUtil jwtProvider;
	
	
	public BlogUserDetailsService(UserRepository userRepository, JwtTokenUtil jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
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
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(
                withUsername(jwtProvider.getUsername(jwtToken))
                .authorities(jwtProvider.getRoles(jwtToken))
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
