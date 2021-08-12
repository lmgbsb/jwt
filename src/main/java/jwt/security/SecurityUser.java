package jwt.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jwt.model.User;
import lombok.Data;

@Data
public class SecurityUser implements UserDetails{

	
	private final User user;
	
	
	public SecurityUser(User user) {
		this.user = user;
	}	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(null);
	}
	// Omitted code
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}	
}
