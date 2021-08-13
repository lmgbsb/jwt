package jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwt.dto.UserDTO;
import jwt.mapper.UserMapper;
import jwt.model.User;
import jwt.repository.UserRepository;

@Service
public class AuthService {
	
	
	private final UserMapper userMapper = UserMapper.INSTANCE;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	public User signup(UserDTO userDTO) {
		User user = userMapper.toModel(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
