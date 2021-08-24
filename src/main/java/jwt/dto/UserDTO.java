package jwt.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

	
	private String id;
	@NotEmpty
    @Size(min = 2, max = 100)
	private String userName;
	private String password;
	@Email(message = "Email deve ser válido")
	@Pattern(regexp=".+@.+\\..+", message = "Email deve ser válido")
	private String email;
}
