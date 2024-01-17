package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
