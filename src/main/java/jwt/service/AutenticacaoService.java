package jwt.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jwt.dto.UsuarioDTO;
import jwt.mapper.UsuarioMapper;
import jwt.model.Usuario;
import jwt.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AutenticacaoService {
	
	
	private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper = UsuarioMapper.INSTANCE;
    
    
    @Transactional
	public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = mapper.toModel(usuarioDTO);
		usuario.setSenha(encodePassword(usuarioDTO.getSenha()));
		usuario.setDataCriacao(Instant.now());
		usuario.setVerificado(false);
		usuarioRepository.save(usuario);
	}
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
