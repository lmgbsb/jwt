package jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jwt.dto.UsuarioDTO;
import jwt.model.User;

@Mapper
public interface UserMapper {
	

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
	User toModel(UsuarioDTO dto);
}
