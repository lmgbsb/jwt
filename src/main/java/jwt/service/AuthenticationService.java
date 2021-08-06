package jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jwt.dto.UsuarioDTO;
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
	public Long cadastrarUsuario(UsuarioDTO usuarioDTO) {
		User usuario = mapper.toModel(usuarioDTO);
		usuario.setSenha(encodePassword(usuarioDTO.getSenha()));
		return (usuarioRepository.save(usuario)).getUserId();
	}
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
