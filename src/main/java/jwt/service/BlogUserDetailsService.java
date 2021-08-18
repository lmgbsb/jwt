package jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jwt.model.User;
import jwt.repository.UserRepository;
import jwt.security.UserPrincipal;

@Service
public class BlogUserDetailsService implements UserDetailsService {

	
	private final UserRepository userRepository;
	
	
	public BlogUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
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
}
