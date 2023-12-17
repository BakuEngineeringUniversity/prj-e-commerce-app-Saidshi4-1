package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDao extends JpaRepository<User, Integer> {
    User findOneByPhoneNumberAndPassword(String phoneNumber, String password);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);

    void deleteByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
}
