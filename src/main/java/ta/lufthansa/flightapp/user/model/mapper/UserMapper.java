package ta.lufthansa.flightapp.user.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.lufthansa.flightapp.user.model.dto.UserDTO;
import ta.lufthansa.flightapp.user.model.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToDTO(UserEntity user);

    UserEntity userDTOToUser(UserDTO userDTO);
}