package jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwt.model.Comentario;
import jwt.model.Post;
import jwt.model.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	
	List<Comentario> findByPost(Post post);
	List<Comentario> findByUsuario(Usuario usuario);
}
