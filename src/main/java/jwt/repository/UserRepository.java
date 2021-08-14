package jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByUserName(String username);
}
