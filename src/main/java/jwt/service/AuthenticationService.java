package jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jwt.dto.UserDTO;
import jwt.mapper.UserMapper;
import jwt.model.User;
import jwt.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
	
	
	private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper = UserMapper.INSTANCE;
    
    
    @Transactional
	public Long cadastrarUsuario(UserDTO usuarioDTO) {
		User usuario = mapper.toModel(usuarioDTO);
		usuario.setPassword(encodePassword(usuarioDTO.getPassword()));
		return (usuarioRepository.save(usuario)).getUserId();
	}
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
