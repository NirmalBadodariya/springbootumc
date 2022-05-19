package com.example.springbootumc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoles {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private UserBean user;

    private int role = 1;
    
    @Override
    public String toString() {
        return "UserRoles [id=" + id + ", role=" + role + ", user=" + user + "]";
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
