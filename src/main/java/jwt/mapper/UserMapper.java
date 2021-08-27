package jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import jwt.dto.UserDTO;
import jwt.model.User;

@Mapper
public interface UserMapper {
	

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
	User toModel(UserDTO dto);
	@Mapping(source = "user.userId", target = "id")
	UserDTO toDto(User user);
}
