package jwt.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Data
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;
	private String titulo;
	@Nullable
    @Lob
    private String descricao;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User usuario;
}
