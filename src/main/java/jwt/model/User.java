package jwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class User {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@NotBlank(message = "Nome do usuário deve ser informado")
	private String name;
	@NotBlank(message = "Senha deve ser informada")
	private String password;
	@Email
    @NotEmpty(message = "Email deve ser informado")
    private String email;
}
