package com.example.springbootumc.service;

import com.example.springbootumc.model.UserBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


public interface SignupService {
    Object addNewUser(UserBean user);

    int checkUser(String email, String pass);

    ArrayList getUserDetails() throws Exception;

    void deleteUser(int userId) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException;

    void updateUser(UserBean user);

    int checkForgotpassDetails(String dob, String securityAns);

    ArrayList<UserBean> getRecentUsersList();

    String checkEmail(String email);

    UserBean getLoggedinUserDetails(String email);

    void changePass(int userId, String newPass);
//    public UserDetails loadUserByUsername(String username);
}
