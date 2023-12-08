package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDao extends JpaRepository<User, Integer> {
    User findOneByPhoneNumberAndPassword(String password, String password1);
}
