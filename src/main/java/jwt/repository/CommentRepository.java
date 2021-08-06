package jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.model.Comment;
import jwt.model.Post;
import jwt.security.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	
	List<Comment> findByPost(Post post);
	List<Comment> findByUsuario(User usuario);
}
