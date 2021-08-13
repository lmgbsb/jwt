package jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
