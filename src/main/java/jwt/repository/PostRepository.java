package jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.model.Post;
import jwt.model.User;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	
	List<Post> findByUsuario(User usuario);
}
