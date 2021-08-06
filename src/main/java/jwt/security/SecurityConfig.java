package jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jwt.repository.UserRepository;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/autenticacao/**", "/css/**").permitAll()
        .anyRequest().authenticated();
	}	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
	}
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null) return user;
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}
}
