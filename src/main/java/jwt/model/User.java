package jwt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import jwt.security.Authority;
import lombok.Data;

@Entity
@Table
@Data
public class User {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name="user_authorities",
    		joinColumns = @JoinColumn(name="userId"),
    		inverseJoinColumns = @JoinColumn(name="authorityId"))    		
    private List<Authority> authorities;
}
