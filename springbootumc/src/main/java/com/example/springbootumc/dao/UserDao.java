package com.example.springbootumc.dao;

import com.example.springbootumc.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface UserDao<T> extends JpaRepository<UserBean , Integer> {
    UserBean getByEmail(String email);
    @Query("select role from UserRoles  where user_id=?1")
    int checkUserType(int id);

    List findByEmailAndPass(String email, String pass);

    List findByDobAndSecurityAns(String dob, String securityAns);

    @Query(value = "select * from users where CreatedTime  >= NOW() - INTERVAL 1 DAY",nativeQuery = true)
    List<Object[]> getRecentUsersList();

    List findByEmail(String email);

}