package jwt.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Authority {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorityId;
	private String name;
}
