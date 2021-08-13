package jwt.dto;

import lombok.Data;


@Data
public class UserDTO {

	
	private String id;
	private String userName;
	private String password;
	private String email;
}
