package jwt.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwt.dto.UserDTO;
import jwt.mapper.UserMapper;
import jwt.model.User;
import jwt.repository.UserRepository;

@Service
public class UserService{
	
	
	private final UserMapper userMapper = UserMapper.INSTANCE;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	
	public UserService(
			UserRepository userRepository, 
			PasswordEncoder passwordEncoder, 
			AuthenticationManager authenticationManager){
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	//create a new user in database
	public User signup(UserDTO userDTO){
		User user = userMapper.toModel(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public Authentication signin(UserDTO userDTO) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
																			userDTO.getUserName(), 
																			userDTO.getPassword());
		return authenticationManager.authenticate(authenticationToken);
	}
	public List<User> listUsers(){
		return userRepository.findAll();
	}
}
