package com.rslezenko.repository;



import com.rslezenko.model.User;
import com.rslezenko.web.dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    User findById(Long id);

    User save(UserRegistrationDto user);

    @Query(value= "SELECT * FROM USER", nativeQuery=true)
    List<User> findAll();

    @Modifying
    @Transactional
    @Query(value= "delete from users_roles  where user_id = ?1", nativeQuery=true)
    void deleteByIdRole(int id);

    @Modifying
    @Transactional
    @Query(value= "delete from user  where id = ?1", nativeQuery=true)
    void deleteById(int id);

    @Modifying
    @Transactional
    @Query(value="update user u set u.first_name = ?1, u.last_name = ?2,u.username = ?3,u.password = ?4,u.email = ?5,u.dob = ?6,u.mobile_num = ?7 where u.id = ?8", nativeQuery=true)
    void setUserInfoById(String first_name, String last_name,String username,String password,String email,String dob, String mobileNum, Integer userId);
}
