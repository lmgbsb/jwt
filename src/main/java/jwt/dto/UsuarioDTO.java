package jwt.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String nome;
	private String senha;
	private String email;
}
