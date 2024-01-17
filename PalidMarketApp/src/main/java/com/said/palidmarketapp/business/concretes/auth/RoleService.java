package com.said.palidmarketapp.business.concretes.auth;

import com.said.palidmarketapp.dataAccess.abstracts.RoleDao;
import com.said.palidmarketapp.mapper.dto.auth.RoleDto;
import com.said.palidmarketapp.mapstruct.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleDao roleRepository;

    public RoleService(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void saveRole(RoleDto roleDto) {
        roleRepository.save(RoleMapper.mapper.mapDtoToEntity(roleDto));
    }

}