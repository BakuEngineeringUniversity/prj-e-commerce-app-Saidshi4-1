package com.said.palidmarketapp.mapstruct;

import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.auth.UserRegisterRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
@Mapping(target="lastName",source="lastName")
@Mapping(target="firstName",source = "firstName")
   User mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);


}
