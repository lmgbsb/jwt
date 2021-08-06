package jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UsuarioDTO;
import jwt.service.AuthenticationService;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

	
	private final AuthenticationService autencicacaoService;
	
	
	public AutenticacaoController(AuthenticationService autencicacaoService) {
		this.autencicacaoService = autencicacaoService;
	}
	@PostMapping("/cadastrarUsuario")
	public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {		
		autencicacaoService.cadastrarUsuario(usuarioDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
