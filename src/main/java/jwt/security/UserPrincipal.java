package jwt.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jwt.model.User;
import lombok.Data;

@Data
public class UserPrincipal implements UserDetails {

	
	private User user;
	
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(null == user.getAuthorities()) {
			return Collections.emptySet();
		}
		return user.getAuthorities().stream()
				.map(a -> new SimpleGrantedAuthority(a.getName()))
				.collect(Collectors.toList());		
	}
	@Override
	@JsonIgnore
	public String getPassword() {
		return this.user.getPassword();
	}
	@Override
	public String getUsername() {
		return this.user.getUserName();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
