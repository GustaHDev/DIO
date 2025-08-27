package br.com.dio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import br.com.dio.model.UserModel;
import br.com.dio.dto.UserDTO;

@Mapper
public interface UserMapper {
    @Mapping(target ="code", source = "id")
    @Mapping(target ="username", source = "name")
    UserModel toModel(final UserDTO userDTO);

    @Mapping(target ="id", source = "code")
    @Mapping(target ="name", source = "username")
    UserDTO toDTO(final UserModel model);
}
