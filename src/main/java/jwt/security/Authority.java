package jwt.security;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import jwt.model.User;
import lombok.Data;

@Entity
@Data
public class Authority {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorityId;
	private String name;
	@ManyToMany(mappedBy = "authorities")
	private List<User> users;	
}
