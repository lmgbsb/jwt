package jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UsuarioDTO;
import jwt.service.AutenticacaoService;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

	
	private final AutenticacaoService autencicacaoService;
	
	
	public AutenticacaoController(AutenticacaoService autencicacaoService) {
		this.autencicacaoService = autencicacaoService;
	}
	@PostMapping("/cadastrarUsuario")
	public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {		
		autencicacaoService.cadastrarUsuario(usuarioDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
