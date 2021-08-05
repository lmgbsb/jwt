package jwt.model;

import lombok.Data;

@Data
public class EmailNotificacao {

	
	private String assunto;
	private String corpo;
	private String recebedor;
}
