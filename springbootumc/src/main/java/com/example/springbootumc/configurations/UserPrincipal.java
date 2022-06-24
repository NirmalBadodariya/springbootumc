package com.example.springbootumc.configurations;

import com.example.springbootumc.model.UserBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private UserBean user;

    public UserPrincipal(UserBean user) {
        this.user = user;

    }
    public UserBean returnUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        if(user.getUserRole()==1){
            System.out.println("came if user");
        authoritySet.add(new SimpleGrantedAuthority("USER"));
            System.out.println(authoritySet.toString());
        }
        else if(user.getUserRole()==2){
            System.out.println("came if Admin");
            authoritySet.add(new SimpleGrantedAuthority("ADMIN"));
            System.out.println(authoritySet.toString());

        }
        return authoritySet;
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        System.out.println("Username:"+user.getEmail());
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
