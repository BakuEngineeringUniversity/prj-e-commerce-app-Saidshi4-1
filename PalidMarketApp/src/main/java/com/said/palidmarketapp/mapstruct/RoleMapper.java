package com.said.palidmarketapp.mapstruct;

import com.said.palidmarketapp.entities.Role;
import com.said.palidmarketapp.mapper.dto.auth.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class RoleMapper {
    public static RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
    public abstract Role mapDtoToEntity(RoleDto roleDto);
    public abstract RoleDto mapEntityToDto(Role roleEntity);


}