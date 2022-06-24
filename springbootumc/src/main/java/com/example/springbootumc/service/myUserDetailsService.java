package com.example.springbootumc.service;

import com.example.springbootumc.configurations.UserPrincipal;
import com.example.springbootumc.dao.UserDao;
import com.example.springbootumc.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Service
public class myUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBean user = userDao.findByEmail(username);
        System.out.println("uswr dekh: "+username);
        if (user==null){
           throw  new UsernameNotFoundException("no user found");
       }
        else {
            int id = user.getId();
            int role =  userDao.checkUserType(id);
            System.out.println(role+"  :eole");
            user.setUserRole(role);
        }
        return new UserPrincipal(user);
    }

}
