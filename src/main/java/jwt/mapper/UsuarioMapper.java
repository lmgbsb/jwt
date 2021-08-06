package jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jwt.dto.UsuarioDTO;
import jwt.model.Usuario;

@Mapper
public interface UsuarioMapper {
	

	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	
	Usuario toModel(UsuarioDTO dto);
}
