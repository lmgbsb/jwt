package jwt.restController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	
	@GetMapping("/test/manager")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String testaPerfilGerente(){
		return "Gerente autenticado e autorizado com sucesso";
	}	
	@GetMapping("/test/administrator")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String testaPerfilAdministrador(){
		return "Administrador autenticado e autorizado com sucesso";
	}
}
