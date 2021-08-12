package jwt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@RequiredArgsConstructor
public class User {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome do usuário deve ser informado")
	private String username;
	@NotBlank(message = "Senha deve ser informada")
	private String password;
	@Email
    @NotEmpty(message = "Email deve ser informado")
    private String email;
	private List getAuthority() {
		return null;
	}
}
