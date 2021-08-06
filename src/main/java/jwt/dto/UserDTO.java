package jwt.dto;

import lombok.Data;

@Data
public class UserDTO {

	
	private String userId;
	private String name;
	private String password;
	private String email;
}
